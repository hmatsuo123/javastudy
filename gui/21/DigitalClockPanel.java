package ex21;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DigitalClockPanel extends JPanel implements Runnable{
	private final int showTimeInterval = 500;
	private Dimension panelSize = new Dimension();
	private Font clockFont = new Font(Font.DIALOG, Font.PLAIN, 100);
	private Font clockSecondFont = new Font(Font.DIALOG, Font.PLAIN, 60);
	private Font dateFont = new Font(Font.DIALOG, Font.PLAIN, 20);
	private final int panelMarginHeight = 10;
	private final int panelMarginWidth = 60;
	private final int clockSecondMargin = 20;
	private Dimension clockTextSize;
	public DigitalClockAnimation digitalClockAnimation;

	// このクラスのコンストラクタは外から呼ばない。newInstanceでインスタンスを作成する
	private DigitalClockPanel() {
		super();
		setBackground(new Color(40, 40, 40));

		JLabel timeLabel = new JLabel(getNowTime());
		timeLabel.setFont(clockFont);
		clockTextSize = timeLabel.getPreferredSize();
		JLabel secondTimeLabel = new JLabel(getNowSecond());
		secondTimeLabel.setFont(clockSecondFont);

		JLabel dateLabel = new JLabel(getNowDate());
		dateLabel.setFont(dateFont);
		Dimension size1 = timeLabel.getPreferredSize();
		Dimension size2 = secondTimeLabel.getPreferredSize();
		Dimension size3 = dateLabel.getPreferredSize();
		panelSize.width = size1.width + clockSecondMargin + size2.width + panelMarginWidth;
		panelSize.height = size1.height + size3.height + panelMarginHeight * 2;
		System.out.println(panelSize.height);
		System.out.println(panelSize.width);
		digitalClockAnimation = new DigitalClockAnimation(panelSize);

		//new Thread(this).start();
	}

	// DigitalClockPanelのインスタンス作成関数
	public static DigitalClockPanel newInstance() {
		DigitalClockPanel panel = new DigitalClockPanel();
		new Thread(panel).start();
		return panel;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		digitalClockAnimation.drawTime(g, Color.GREEN, clockFont);
		setDigitalLayout(g);

		g.setFont(dateFont);
		g.setColor(new Color(180, 180, 180));
		// 中央に表示するためマジックナンバーで目視調整
		g.drawString(getNowDate(), 185, clockFont.getSize() + dateFont.getSize() + panelMarginHeight + 14);
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

	/**
	 * パネル状に黒色の菱形を連続で表示する
	 */
	private void setDigitalLayout(Graphics g) {
		g.setColor(Color.BLACK);
		// 菱形の直系幅
		int length = 4;
		int idxX = 0;
		int idxY = 0;
		int xPoints[] = {length / 2, length, length / 2, 0};
		int yPoints[] = {0, length / 2, length, length / 2};

		while (idxY < this.panelSize.height + panelMarginHeight) {
			while (idxX < this.panelSize.getWidth() + panelMarginWidth) {
				g.fillPolygon(xPoints, yPoints, 4);
				idxX += length;
				xPoints[0] += length;
				xPoints[1] += length;
				xPoints[2] += length;
				xPoints[3] += length;
			}
			idxX = 0;
			xPoints[0] = length / 2;
			xPoints[1] = length;
			xPoints[2] = length / 2;
			xPoints[3] = 0;

			idxY += length;
			yPoints[0] += length;
			yPoints[1] += length;
			yPoints[2] += length;
			yPoints[3] += length;
		}
	}

	/**
	 * 時計を左にスライドさせて表示するクラス
	 */
	public class DigitalClockAnimation {
		private Dimension ownerPanelSize = new Dimension();
		public Boolean isAnimationOn = true;
		public int moveLength = 8;
		private int defaultTimeX;
		private int defaultTimeY;
		private int defaultSecondTimeX;
		private int defaultSecondTimeY;
		private int nowTimeX;
		private int nowTime2X;
		private int nowSecondTimeX;
		private int nowSecondTime2X;

		public DigitalClockAnimation(Dimension size) {
			ownerPanelSize = size;
			defaultTimeX = panelMarginWidth / 2;
			defaultTimeY = clockFont.getSize() + panelMarginHeight / 2;
			defaultSecondTimeX = panelMarginWidth / 2 + (int)clockTextSize.getWidth() + clockSecondMargin;
			defaultSecondTimeY = clockFont.getSize() + panelMarginHeight / 2;
			initNowPosition();
		}

		private void initNowPosition() {
			nowTimeX = defaultTimeX;
			nowTime2X = defaultTimeX + (int)panelSize.getWidth() + panelMarginWidth / 2;
			nowSecondTimeX = defaultSecondTimeX;
			nowSecondTime2X = defaultSecondTimeX + (int)panelSize.getWidth() + panelMarginWidth / 2;
		}

		public void drawTime(Graphics g, Color fontColor, Font font) {
			int _moveLength = moveLength;
			if (isAnimationOn) {
				g.setColor(fontColor);
				g.setFont(font);
				nowTimeX -= _moveLength;
				if (nowTimeX + (int)ownerPanelSize.getWidth() < 0) {
					nowTimeX = (int)ownerPanelSize.getWidth() + panelMarginWidth;
				}
				g.drawString(getNowTime(), nowTimeX, defaultTimeY);
				nowTime2X -= _moveLength;
				if (nowTime2X + (int)ownerPanelSize.getWidth() < 0) {
					nowTime2X = (int)ownerPanelSize.getWidth() + panelMarginWidth;
				}
				g.drawString(getNowTime(), nowTime2X, defaultTimeY);

				g.setFont(clockSecondFont);
				nowSecondTimeX -= _moveLength;
				if (nowSecondTimeX + (int)ownerPanelSize.getWidth() < 0) {
					nowSecondTimeX = (int)ownerPanelSize.getWidth() + panelMarginWidth;
				}
				g.drawString(getNowSecond(), nowSecondTimeX, defaultSecondTimeY);
				nowSecondTime2X -= _moveLength;
				if (nowSecondTime2X + (int)ownerPanelSize.getWidth() < 0) {
					nowSecondTime2X = (int)ownerPanelSize.getWidth() + panelMarginWidth;
				}
				g.drawString(getNowSecond(), nowSecondTime2X, defaultSecondTimeY);
			} else {
				g.setColor(fontColor);
				g.setFont(font);
				g.drawString(getNowTime(), defaultTimeX, defaultTimeY);
				g.setFont(clockSecondFont);
				g.drawString(getNowSecond(), defaultSecondTimeX, defaultSecondTimeY);
			}
		}

		public void changeAnimationOn() {
			System.out.println("changeAnimationOn");
			isAnimationOn = true;
		}

		public void changeAnimationOff() {
			System.out.println("changeAnimationOff");
			isAnimationOn = false;
			initNowPosition();
		}
	}

	/**
	 * 現在の時間を取得する
	 * @return hour:minute形式のString文字列
	 */
	private String getNowTime() {
		Calendar now = Calendar.getInstance();
		int h = now.get(Calendar.HOUR_OF_DAY);
		int m = now.get(Calendar.MINUTE);
		return String.format("%02d:%02d", h, m);
	}

	/**
	 * 現在の秒を取得する
	 * @return hour:minute形式のString文字列
	 */
	private String getNowSecond() {
		Calendar now = Calendar.getInstance();
		int s= now.get(Calendar.SECOND);
		return String.format("%02d", s);
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
			dayOfWeek = "日";
        case Calendar.MONDAY:
        	dayOfWeek = "月";
        case Calendar.TUESDAY:
        	dayOfWeek = "火";
        case Calendar.WEDNESDAY:
        	dayOfWeek = "水";
        case Calendar.THURSDAY:
        	dayOfWeek = "木";
        case Calendar.FRIDAY:
        	dayOfWeek = "金";
        case Calendar.SATURDAY:
        	dayOfWeek =  "土";
		}
		return String.format("%04d年%02d月%02d日(%s)", y, m, d, dayOfWeek);
	}
}
