package puzzle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.EventListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DigitalClockPanel extends JPanel implements Runnable{
	private final int showTimeInterval = 500;
	private final Dimension panelSize = new Dimension();
	public int clockFontSize = 55;
	public final String defaultClockFontName = "Imprint MT Shadow";
	public Font clockFont = new Font(defaultClockFontName, Font.PLAIN, clockFontSize);
	private Font clockSecondFont = new Font(Font.DIALOG, Font.PLAIN, clockFontSize / 2);
	public final Color defaultClockColor = Color.GREEN;
	public Color clockFontColor = defaultClockColor;
	public Color clockBackgroundColor = Color.BLACK;
	private int panelMarginWidth = 40;
	public Animation digitalClockAnimation;
	private DigitalClockPreferences prefs;
	private IClockEventListener clockEventListener = null;

	// このクラスのコンストラクタは外から呼ばない。newInstanceでインスタンスを作成する
	private DigitalClockPanel(JFrame owner, int panelWidth, int panelHeight) {
		super();
		// ダブルバッファリングを行うとなぜかちらついてしまう
		setDoubleBuffered(false);
		panelSize.width = panelWidth;
		panelSize.height = panelHeight;
		setBackground(clockBackgroundColor);

		// 前回の設定を反映
		String clockFontFamily = "Imprint MT Shadow";
		prefs = new DigitalClockPreferences();
		if (prefs.getFontFamily() != null) {
			clockFontFamily = prefs.getFontFamily();
		}
		clockFont = new Font(clockFontFamily, Font.PLAIN, clockFontSize);
		if (prefs.getFontColor() != null) {
			clockFontColor = new Color(Integer.parseInt(prefs.getFontColor()));
		}

		digitalClockAnimation = new Animation(panelSize);
		calcPanelMargin();
	}

	// DigitalClockPanelのインスタンス作成関数
	public static DigitalClockPanel newInstance(JFrame owner, int panelWidth, int panelHeight) {
		DigitalClockPanel panel = new DigitalClockPanel(owner, panelWidth, panelHeight);
		new Thread(panel).start();
		return panel;
	}

	boolean isRepaintComponent = true;

	public BufferedImage getImage() {
		Component comp = (Component)this;
		int w = panelSize.width;
		int h = panelSize.height;
		BufferedImage image = new BufferedImage( w, h, BufferedImage.TYPE_INT_RGB );

		try {
			isRepaintComponent = false;
			Graphics2D g2 = image.createGraphics();
			comp.paint( g2 );
			g2.dispose();
		} finally {
			isRepaintComponent = true;
		}


		return image;
	}

	public void setClockFont(String font, int size) {
		clockFontSize = size;
		clockFont = new Font(font, Font.PLAIN, clockFontSize);
		clockSecondFont = new Font(font, Font.PLAIN, clockFontSize / 2);
		calcPanelMargin();
		digitalClockAnimation.setDefaultTimeX(panelMarginWidth);
	}

	public void setClockFontColer(Color color) {
		if (color != null)
			clockFontColor = color;
	}

	public void setBackgroundColor(Color color) {
		if (color != null)
			clockBackgroundColor = color;
	}

	private void calcPanelMargin() {
		JLabel timeLabel = new JLabel(getNowTime());
		timeLabel.setFont(clockFont);
		Dimension size1 = timeLabel.getPreferredSize();
		panelMarginWidth = (panelSize.width - size1.width) / 2;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		digitalClockAnimation.draw(g, clockFontColor, clockFont);
		calcPanelMargin();

		// 時計が更新され、かつイベントフック済の場合(Component.PaintでもpaintComponentが発生するため)
		if (isRepaintComponent && clockEventListener != null)
			clockEventListener.ClockUpdated();
	};

	@Override
	public Dimension getPreferredSize() {
		return panelSize;
	}

	@Override
	public void run() {
		while(true) {
			this.repaint();
			try {
				Thread.sleep(showTimeInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public enum AnimationMode {
		slideMode,
		randomMode
	}

	/**
	 * 時計を左にスライドさせて表示するクラス
	 */
	public class Animation {
		private Dimension ownerPanelSize = new Dimension();
		public Boolean isAnimationOn = false;
		public int moveLength = 8;
		public AnimationMode mode = AnimationMode.slideMode;
		private String message1 = "Digital Clock";
		public String message2 = "Slide Pazzle Game";
		private String timeHeader = "TIME:";
		private String am = "AM";
		private String pm = "PM";

		// スライドモードで使用するフィールド
		private int defaultTimeX;
		private int defaultTimeY;
		private int nowTimeX;
		private int nowTime2X;

		private int defaultMessage1X;
		private int defaultMessage1Y;
		private int nowMessage1X;
		private int nowMessage1X2;

		private int defaultMessage2X;
		private int defaultMessage2Y;
		private int nowMessage2X;
		private int nowMessage2X2;

		private int defaultWeekX;
		private int defaultWeekY;
		private int nowWeekX;
		private int nowWeekX2;

		// ランダムモードで使用するモード
		private final int fontSize = 28;  // TODO: フォントを変更しても位置がそろえられるようにする
		private final int colonSize = 13;  // TODO: フォントを変更しても位置がそろえられるようにする
		private int hour1PositionX;
		private int hour1PositionY;
		private int hour1PositionXDir;
		private int hour1PositionYDir;
		private int hour2PositionX;
		private int hour2PositionY;
		private int hour2PositionXDir;
		private int hour2PositionYDir;
		private int minute1PositionX;
		private int minute1PositionY;
		private int minute1PositionXDir;
		private int minute1PositionYDir;
		private int minute2PositionX;
		private int minute2PositionY;
		private int minute2PositionXDir;
		private int minute2PositionYDir;
		private int second1PositionX;
		private int second1PositionY;
		private int second1PositionXDir;
		private int second1PositionYDir;
		private int second2PositionX;
		private int second2PositionY;
		private int second2PositionXDir;
		private int second2PositionYDir;
		private int colon1PositionX;
		private int colon1PositionY;
		private int colon1PositionXDir;
		private int colon1PositionYDir;
		private int colon2PositionX;
		private int colon2PositionY;
		private int colon2PositionXDir;
		private int colon2PositionYDir;

		public Animation(Dimension size) {
			ownerPanelSize = size;
			defaultTimeX = panelMarginWidth;
			defaultTimeY = 203;
			defaultMessage1X = 20;
			defaultMessage1Y = 45;
			defaultMessage2X = 75;
			defaultMessage2Y = 92;
			defaultWeekX = 30;
			defaultWeekY = 255;

			initSlideMode();
			initRandomMode();
		}

		public void setDefaultTimeX(int x) {
			defaultTimeX = panelMarginWidth;
		}

		private void initSlideMode() {
			nowTimeX = defaultTimeX;
			nowTime2X = defaultTimeX + (int)panelSize.getWidth() + panelMarginWidth / 2;
			nowMessage1X = defaultMessage1X;
			nowMessage1X2 = defaultMessage1X + (int)panelSize.getWidth() + panelMarginWidth / 2;
			nowMessage2X = defaultMessage2X;
			nowMessage2X2 = defaultMessage2X + (int)panelSize.getWidth() + panelMarginWidth / 2;
			nowWeekX = defaultWeekX;
			nowWeekX2 = defaultWeekX + (int)panelSize.getWidth() + panelMarginWidth / 2;
		}

		private void initRandomMode() {
			hour1PositionX = defaultTimeX;
			hour1PositionY = defaultTimeY;
			hour2PositionX = hour1PositionX + fontSize;
			hour2PositionY = defaultTimeY;
			colon1PositionX = hour2PositionX + fontSize;
			colon1PositionY = defaultTimeY;
			minute1PositionX = colon1PositionX + colonSize;
			minute1PositionY = defaultTimeY;
			minute2PositionX = minute1PositionX + fontSize;
			minute2PositionY = defaultTimeY;
			colon2PositionX = minute2PositionX + fontSize;
			colon2PositionY = defaultTimeY;
			second1PositionX = colon2PositionX + colonSize;
			second1PositionY = defaultTimeY;
			second2PositionX = second1PositionX + fontSize;
			second2PositionY = defaultTimeY;

			// 進行方向を初期化(進行方向はランダム)
			int _moveLength = moveLength;
			hour1PositionXDir = initDirection(_moveLength);
			hour1PositionYDir = initDirection(_moveLength);
			hour2PositionXDir = initDirection(_moveLength);
			hour2PositionYDir = initDirection(_moveLength);
			minute1PositionXDir = initDirection(_moveLength);
			minute1PositionYDir = initDirection(_moveLength);
			minute2PositionXDir = initDirection(_moveLength);
			minute2PositionYDir = initDirection(_moveLength);
			second1PositionXDir = initDirection(_moveLength);
			second1PositionYDir = initDirection(_moveLength);
			second2PositionXDir = initDirection(_moveLength);
			second2PositionYDir = initDirection(_moveLength);
			colon1PositionXDir = initDirection(_moveLength);
			colon1PositionYDir = initDirection(_moveLength);
			colon2PositionXDir = initDirection(_moveLength);
			colon2PositionYDir = initDirection(_moveLength);
		}

		private int initDirection(int moveLeng) {
			// 進行方向はランダム
			return Math.random() > 0.5 ? moveLeng : -moveLeng;
		}

		public void draw(Graphics g, Color clockFontColor, Font clockFont) {
			int clockMoveLength = moveLength;
			int messageMoveLength = clockMoveLength + 3;
			int weekMoveLength = clockMoveLength - 3;
			if (weekMoveLength < 0)
				weekMoveLength = clockMoveLength;

			if (isAnimationOn) {
				if (mode == AnimationMode.slideMode) {
					// メッセージを再表示
					g.setFont(new Font("Gabriola", Font.PLAIN, 35));
					g.setColor(Color.CYAN);
					nowMessage1X -= messageMoveLength;
					if (nowMessage1X + (int)ownerPanelSize.getWidth() < 0) {
						nowMessage1X = (int)ownerPanelSize.getWidth() + defaultMessage1X;
					}
					g.drawString(message1, nowMessage1X, defaultMessage1Y);
					if (nowMessage1X2 + (int)ownerPanelSize.getWidth() < 0) {
						nowMessage1X2 = (int)ownerPanelSize.getWidth() + defaultMessage1X;
					}
					g.drawString(message1, nowMessage1X2, defaultMessage1Y);

					nowMessage2X -= messageMoveLength;
					if (nowMessage2X + (int)ownerPanelSize.getWidth() < 0) {
						nowMessage2X = (int)ownerPanelSize.getWidth() + defaultMessage2X;
					}
					g.drawString(message2, nowMessage2X, defaultMessage2Y);
					if (nowMessage2X2 + (int)ownerPanelSize.getWidth() < 0) {
						nowMessage2X2 = (int)ownerPanelSize.getWidth() + defaultMessage2X;
					}
					g.drawString(message2, nowMessage2X2, defaultMessage2Y);

					// 時計を再表示
					nowTimeX -= clockMoveLength;
					if (nowTimeX + (int)ownerPanelSize.getWidth() < 0) {
						nowTimeX = (int)ownerPanelSize.getWidth() + panelMarginWidth;
					}
					nowTime2X -= clockMoveLength;
					if (nowTime2X + (int)ownerPanelSize.getWidth() < 0) {
						nowTime2X = (int)ownerPanelSize.getWidth() + panelMarginWidth;
					}
					g.setFont(new Font("Gabriola", Font.PLAIN, 20));
					g.setColor(clockFontColor);
					g.drawString(timeHeader, nowTimeX, 150);
					g.drawString(timeHeader, nowTime2X, 150);
					String time = am;
					int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
					if (h > 11) time = pm;
					g.drawString(time, nowTimeX + 50, 150);
					g.drawString(time, nowTime2X + 50, 150);

					g.setColor(clockFontColor);
					g.setFont(clockFont);
					g.drawString(getNowTime(), nowTimeX, defaultTimeY);
					g.drawString(getNowTime(), nowTime2X, defaultTimeY);

					// 曜日を再表示
					g.setFont(new Font("Goudy Old Style", Font.PLAIN, 20));
					g.setColor(Color.WHITE);
					nowWeekX -= weekMoveLength;
					if (nowWeekX + (int)ownerPanelSize.getWidth() < 0) {
						nowWeekX = (int)ownerPanelSize.getWidth() + defaultMessage1X;
					}
					g.drawString(getNowDate(), nowWeekX, defaultWeekY);
					if (nowWeekX2 + (int)ownerPanelSize.getWidth() < 0) {
						nowWeekX2 = (int)ownerPanelSize.getWidth() + defaultMessage1X;
					}
					g.drawString(getNowDate(), nowWeekX2, defaultWeekY);
				} else if (mode == AnimationMode.randomMode) {
					g.setFont(new Font("Gabriola", Font.PLAIN, 35));
					g.setColor(Color.CYAN);
					g.drawString(message1, defaultMessage1X, defaultMessage1Y);
					g.drawString(message2, defaultMessage2X, defaultMessage2Y);

					g.setFont(new Font("Gabriola", Font.PLAIN, 20));
					g.setColor(clockFontColor);
					g.drawString(timeHeader, defaultTimeX, 150);
					String time = am;
					Calendar now = Calendar.getInstance();
					int h = now.get(Calendar.HOUR_OF_DAY);
					if (h > 11) time = pm;
					g.drawString(time, defaultTimeX + 50, 150);

					g.setFont(new Font("Goudy Old Style", Font.PLAIN, 20));
					g.setColor(Color.WHITE);
					g.drawString(getNowDate(), defaultWeekX, defaultWeekY);

					g.setColor(clockFontColor);
					g.setFont(clockFont);
					int m = now.get(Calendar.MINUTE);
					int s= now.get(Calendar.SECOND);

					// 時計を表示
					calPositionAndDir();
					g.drawString(String.valueOf(h / 10), hour1PositionX, hour1PositionY);
					g.drawString(String.valueOf(h % 10), hour2PositionX, hour2PositionY);
					g.drawString(":", colon1PositionX, colon1PositionY);
					g.drawString(String.valueOf(m / 10), minute1PositionX, minute1PositionY);
					g.drawString(String.valueOf(m % 10), minute2PositionX, minute2PositionY);
					g.drawString(":", colon2PositionX, colon2PositionY);
					g.drawString(String.valueOf(s / 10), second1PositionX, second1PositionY);
					g.drawString(String.valueOf(s % 10), second2PositionX, second2PositionY);
				}
			} else {
				g.setFont(new Font("Gabriola", Font.PLAIN, 35));
				g.setColor(Color.CYAN);
				g.drawString(message1, defaultMessage1X, defaultMessage1Y);
				g.drawString(message2, defaultMessage2X, defaultMessage2Y);

				g.setFont(new Font("Gabriola", Font.PLAIN, 20));
				g.setColor(clockFontColor);
				g.drawString(timeHeader, defaultTimeX, 150);
				String time = am;
				int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
				if (h > 11) time = pm;
				g.drawString(time, defaultTimeX + 50, 150);

				g.setColor(clockFontColor);
				g.setFont(clockFont);
				g.drawString(getNowTime(), defaultTimeX, defaultTimeY);
				g.setFont(clockSecondFont);

				g.setFont(new Font("Goudy Old Style", Font.PLAIN, 20));
				g.setColor(Color.WHITE);
				g.drawString(getNowDate(), defaultWeekX, defaultWeekY);
			}
		}

		// アニメーションの速度はユーザーが変更できるため再計算するためのメソッド
		private void calcAllDirection(int moveLeng) {
			hour1PositionXDir = hour1PositionXDir > 0 ? moveLeng : -moveLeng;
			hour1PositionYDir = hour1PositionYDir > 0 ? moveLeng : -moveLeng;
			hour2PositionXDir = hour2PositionXDir > 0 ? moveLeng : -moveLeng;
			hour2PositionYDir = hour2PositionYDir > 0 ? moveLeng : -moveLeng;
			minute1PositionXDir = minute1PositionXDir > 0 ? moveLeng : -moveLeng;
			minute1PositionYDir = minute1PositionYDir > 0 ? moveLeng : -moveLeng;
			minute2PositionXDir = minute2PositionXDir > 0 ? moveLeng : -moveLeng;
			minute2PositionYDir = minute2PositionYDir > 0 ? moveLeng : -moveLeng;
			second1PositionXDir = second1PositionXDir > 0 ? moveLeng : -moveLeng;
			second1PositionYDir = second1PositionYDir > 0 ? moveLeng : -moveLeng;
			second2PositionXDir = second2PositionXDir > 0 ? moveLeng : -moveLeng;
			second2PositionYDir = second2PositionYDir > 0 ? moveLeng : -moveLeng;
			colon1PositionXDir = colon1PositionXDir > 0 ? moveLeng : -moveLeng;
			colon1PositionYDir = colon1PositionYDir > 0 ? moveLeng : -moveLeng;
			colon2PositionXDir = colon2PositionXDir > 0 ? moveLeng : -moveLeng;
			colon2PositionYDir = colon2PositionYDir > 0 ? moveLeng : -moveLeng;
		}

		// ランダムモードアニメーションの位置と進行方向を計算する
		private void calPositionAndDir() {
			calcAllDirection(moveLength);
			hour1PositionX += hour1PositionXDir;
			hour1PositionY += hour1PositionYDir;
			// 壁に衝突すれば反射
			if (hour1PositionX >= (ownerPanelSize.width - fontSize) || hour1PositionX <= 0) {
				hour1PositionXDir =- hour1PositionXDir;
			}
			if (hour1PositionY >= ownerPanelSize.height || hour1PositionY - fontSize <= 0) {
				hour1PositionYDir =- hour1PositionYDir;
			}
			hour2PositionX += hour2PositionXDir;
			hour2PositionY += hour2PositionYDir;
			if (hour2PositionX >= (ownerPanelSize.width - fontSize) || hour2PositionX <= 0) {
				hour2PositionXDir =- hour2PositionXDir;
			}
			if (hour2PositionY >= ownerPanelSize.height || hour2PositionY - fontSize <= 0) {
				hour2PositionYDir =- hour2PositionYDir;
			}

			minute1PositionX += minute1PositionXDir;
			minute1PositionY += minute1PositionYDir;
			if (minute1PositionX >= (ownerPanelSize.width - fontSize) || minute1PositionX <= 0) {
				minute1PositionXDir =- minute1PositionXDir;
			}
			if (minute1PositionY >= ownerPanelSize.height || minute1PositionY - fontSize <= 0) {
				minute1PositionYDir =- minute1PositionYDir;
			}
			minute2PositionX += minute2PositionXDir;
			minute2PositionY += minute2PositionYDir;
			if (minute2PositionX >= (ownerPanelSize.width - fontSize) || minute2PositionX <= 0) {
				minute2PositionXDir =- minute2PositionXDir;
			}
			if (minute2PositionY >= ownerPanelSize.height || minute2PositionY - fontSize <= 0) {
				minute2PositionYDir =- minute2PositionYDir;
			}

			second1PositionX += second1PositionXDir;
			second1PositionY += second1PositionYDir;
			if (second1PositionX >= (ownerPanelSize.width - fontSize) || second1PositionX <= 0) {
				second1PositionXDir =- second1PositionXDir;
			}
			if (second1PositionY >= ownerPanelSize.height || second1PositionY - fontSize <= 0) {
				second1PositionYDir =- second1PositionYDir;
			}
			second2PositionX += second2PositionXDir;
			second2PositionY += second2PositionYDir;
			if (second2PositionX >= (ownerPanelSize.width - fontSize) || second2PositionX <= 0) {
				second2PositionXDir =- second2PositionXDir;
			}
			if (second2PositionY >= ownerPanelSize.height || second2PositionY - fontSize <= 0) {
				second2PositionYDir =- second2PositionYDir;
			}

			colon1PositionX += colon1PositionXDir;
			colon1PositionY += colon1PositionYDir;
			if (colon1PositionX >= (ownerPanelSize.width - colonSize) || colon1PositionX <= 0) {
				colon1PositionXDir =- colon1PositionXDir;
			}
			if (colon1PositionY >= ownerPanelSize.height || colon1PositionY - colonSize <= 0) {
				colon1PositionYDir =- colon1PositionYDir;
			}
			colon2PositionX += colon2PositionXDir;
			colon2PositionY += colon2PositionYDir;
			if (colon2PositionX >= (ownerPanelSize.width - colonSize) || colon2PositionX <= 0) {
				colon2PositionXDir =- colon2PositionXDir;
			}
			if (colon2PositionY >= ownerPanelSize.height || colon2PositionY -colonSize <= 0) {
				colon2PositionYDir =- colon2PositionYDir;
			}
		}

		public void changeAnimationOn() {
			isAnimationOn = true;
		}

		public void changeAnimationOff() {
			isAnimationOn = false;
			initSlideMode();
			initRandomMode();
		}
	}

	/**
	 * 現在の時間を取得する
	 * @return hour:minute:second形式のString文字列
	 */
	private String getNowTime() {
		Calendar now = Calendar.getInstance();
		int h = now.get(Calendar.HOUR_OF_DAY);
		int m = now.get(Calendar.MINUTE);
		int s= now.get(Calendar.SECOND);
		return String.format("%02d:%02d:%02d", h, m, s);
	}

	/**
	 * 現在の日付を取得する
	 * @return year年month月date日(date of week)形式のString文字列
	 */
	private String getNowDate() {
		Calendar now = Calendar.getInstance();
		int y = now.get(Calendar.YEAR);
		int m = now.get(Calendar.MONTH) + 1;
		int d= now.get(Calendar.DATE);

		String dayOfWeek = "";
		switch (now.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			dayOfWeek = "SUNDAY";
		case Calendar.MONDAY:
			dayOfWeek = "MONDAY";
		case Calendar.TUESDAY:
			dayOfWeek = "TUESDAY";
		case Calendar.WEDNESDAY:
			dayOfWeek = "WEDNESDAY";
		case Calendar.THURSDAY:
			dayOfWeek = "THURSDAY";
		case Calendar.FRIDAY:
			dayOfWeek = "FRIDAY";
		case Calendar.SATURDAY:
			dayOfWeek =  "SATURDAY";
		}
		return String.format("%s, %02d/%02d, %04d", dayOfWeek, m, d, y);
	}

	/**
	 * 時計のイベントリスナーインターフェース
	 */
	public interface IClockEventListener extends EventListener {
		public void ClockUpdated();
	}

	/**
	 * リスナーを追加する
	 * @param listener
	 */
	public void setListener(IClockEventListener listener){
		clockEventListener = listener;
	}

	/**
	 * リスナーを削除する
	 * @param listener
	 */
	public void removeListener(){
		clockEventListener = null;
	}
}
