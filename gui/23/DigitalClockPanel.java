package ex23;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JWindow;

public class DigitalClockPanel extends JWindow implements Runnable, ActionListener {
	private JPanel ownerPanel;
	private final int showTimeInterval = 500;
	private Dimension panelSize = new Dimension();
	public int clockFontSize = 100;
	public Font clockFont = new Font(Font.DIALOG, Font.PLAIN, clockFontSize);
	//private Font clockSecondFont = new Font(Font.DIALOG, Font.PLAIN, 60);
	private Font clockSecondFont = new Font(Font.DIALOG, Font.PLAIN, clockFontSize / 2);
	private Font dateFont = new Font(Font.DIALOG, Font.PLAIN, 20);
	private Color clockFontColor = Color.GREEN;
	private Color clockBackgroundColor = new Color(0, 0, 0);
	private final int panelMarginHeight = 10;
	private final int panelMarginWidth = 60;
	private final int clockSecondMargin = 20;
	private Dimension clockTextSize;
	public DigitalClockAnimation digitalClockAnimation;
	private int X;
	private int Y;

	enum MenuContents {
		EXIT("Exit"),
		FONT("Font"),
		FONTSIZE("Font Size"),
		FONTCOLOR("Font Color"),
		BACKGROUNDCOLOR("Background Color");

		//UI上に表示する文字
		String text;

		MenuContents(String text) {
			this.text = text;
		}

		String getText() {
			return text;
		}
	}

	//マウスクリック、ドラッグイベント
	class ClockMouseAdapter extends MouseAdapter {
		DigitalClockPanel owner;
		JPopupMenu popupMenu;

		public ClockMouseAdapter(DigitalClockPanel clockWindow, JPopupMenu popupMenu) {
			this.owner = clockWindow;
			this.popupMenu = popupMenu;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			owner.X = e.getX();
			owner.Y = e.getY();
			if (e.isPopupTrigger()) {
				popupMenu.show(owner, e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.isPopupTrigger()) {
				popupMenu.show(owner, e.getX(), e.getY());
			}
		}
	}

	class ClockMouseMotionAdapter extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			int _x = getX() + e.getX() -X;
			int _y = getY() + e.getY() - Y;
			//owner.SetParentLocation(_x, _y);
			setLocation(_x, _y);
		}
	}

	// このクラスのコンストラクタは外から呼ばない。newInstanceでインスタンスを作成する
	private DigitalClockPanel() {
		super();
		//this.owner = owner;
		ownerPanel = new JPanel();
		ownerPanel.setBackground(clockBackgroundColor);

		JLabel timeLabel = new JLabel(getNowTime());
		timeLabel.setFont(clockFont);
		clockTextSize = timeLabel.getPreferredSize();
		//JLabel secondTimeLabel = new JLabel(getNowSecond());
		JLabel secondTimeLabel = new JLabel();
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


		JPopupMenu popupMenu = new JPopupMenu();
		//[Font]
		JMenu fontMenu = new JMenu(MenuContents.FONT.getText());
		popupMenu.add(fontMenu);
		//[Font]-[etc...]
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		for (Font f : ge.getAllFonts()) {
			JMenuItem item = new JMenuItem(f.getName());
			item.addActionListener(new ChangeFontActionListener(this));
			fontMenu.add(item);
		}

		//[Font Size]
		JMenu fontSizeMenu = new JMenu(MenuContents.FONTSIZE.getText());
		popupMenu.add(fontSizeMenu);
		//[Font Size]-[etc...]
		ChangeFontSizeActionListener fontSizeListener = new ChangeFontSizeActionListener(this);
		JMenuItem itemSmall = new JMenuItem("100");
		itemSmall.addActionListener(fontSizeListener);
		fontSizeMenu.add(itemSmall);
		JMenuItem itemBig = new JMenuItem("300");
		itemBig.addActionListener(fontSizeListener);
		fontSizeMenu.add(itemBig);
		JMenuItem itemLarge = new JMenuItem("500");
		itemLarge.addActionListener(fontSizeListener);
		fontSizeMenu.add(itemLarge);

		//[Font Color]
		JMenuItem fontColorMenu = new JMenuItem(MenuContents.FONTCOLOR.getText());
		fontColorMenu.addActionListener(new ChangeFontColorActionListener(this));
		popupMenu.add(fontColorMenu);

		//[Background Color]
		JMenuItem backgroundColorMenu = new JMenuItem(MenuContents.BACKGROUNDCOLOR.getText());
		backgroundColorMenu.addActionListener(new ChangeBackgroundColorActionListener(this));
		popupMenu.add(backgroundColorMenu);

		//[----]
		popupMenu.addSeparator();

		//[Exit]
		JMenuItem exitMenu = new JMenuItem(MenuContents.EXIT.getText());
		exitMenu.addActionListener(this);
		popupMenu.add(exitMenu);

		ownerPanel.add(popupMenu);
		this.add(ownerPanel);
		this.addMouseListener(new ClockMouseAdapter(this, popupMenu));
		this.addMouseMotionListener(new ClockMouseMotionAdapter());
		pack();
		setVisible(true);

		//new Thread(this).start();
	}

	// DigitalClockPanelのインスタンス作成関数
	public static DigitalClockPanel newInstance(DigitalClock owner) {
		DigitalClockPanel panel = new DigitalClockPanel();
		new Thread(panel).start();
		return panel;
	}

	public void setClockFont(String font, int size) {
		if (size != 0)
			clockFontSize = size;
		clockFont = new Font(font != "" ? font : clockFont.getFamily(), Font.PLAIN, clockFontSize);
		clockSecondFont = new Font(font, Font.PLAIN, clockFontSize / 2);
	}

	public void setClockFontColer(Color color) {
		if (color != null)
			clockFontColor = color;
	}

	public void setBackgroundColor(Color color) {
		if (color != null)
			clockBackgroundColor = color;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		digitalClockAnimation.drawTime(g, clockFontColor, clockFont);
		//setDigitalLayout(g);

		g.setFont(dateFont);
		g.setColor(new Color(180, 180, 180));
		// 中央に表示するためマジックナンバーで目視調整
		//g.drawString(getNowDate(), 185, clockFont.getSize() + dateFont.getSize() + panelMarginHeight + 14);

		ownerPanel.setBackground(clockBackgroundColor);
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
		this.setPreferredSize(panelSize);
		pack();
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

	public void reDisplay() {
		this.repaint();
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
		public Boolean isAnimationOn = false;
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
				g.drawString(getNowTime(), nowTimeX, clockFont.getSize() + panelMarginHeight / 2);
				nowTime2X -= _moveLength;
				if (nowTime2X + (int)ownerPanelSize.getWidth() < 0) {
					nowTime2X = (int)ownerPanelSize.getWidth() + panelMarginWidth;
				}
				g.drawString(getNowTime(), nowTime2X, clockFont.getSize() + panelMarginHeight / 2);

				g.setFont(clockSecondFont);
				nowSecondTimeX -= _moveLength;
				if (nowSecondTimeX + (int)ownerPanelSize.getWidth() < 0) {
					nowSecondTimeX = (int)ownerPanelSize.getWidth() + panelMarginWidth;
				}
				g.drawString(getNowSecond(), nowSecondTimeX, clockFont.getSize() + panelMarginHeight / 2);
				nowSecondTime2X -= _moveLength;
				if (nowSecondTime2X + (int)ownerPanelSize.getWidth() < 0) {
					nowSecondTime2X = (int)ownerPanelSize.getWidth() + panelMarginWidth;
				}
				g.drawString(getNowSecond(), nowSecondTime2X, clockFont.getSize() + panelMarginHeight / 2);
			} else {
				g.setColor(fontColor);
				g.setFont(font);
				g.drawString(getNowTime(), defaultTimeX, clockFont.getSize() + panelMarginHeight / 2);
				g.setFont(clockSecondFont);
				//g.drawString(getNowSecond(), defaultSecondTimeX, defaultSecondTimeY);
			}
		}

		public void changeAnimationOn() {
			isAnimationOn = true;
		}

		public void changeAnimationOff() {
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
		int s= now.get(Calendar.SECOND);
		return String.format("%02d:%02d:%02d", h, m, s);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == MenuContents.EXIT.getText()) {
			//owner.SystemExit();
			dispose();
			System.exit(0);
		}
	}
}
