package ex21;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DigitalClockPanel extends JPanel implements Runnable{
	private Dimension panelSize = new Dimension();
	private Font clockFont = new Font(Font.DIALOG, Font.PLAIN, 100);
	private Font clockSecondFont = new Font(Font.DIALOG, Font.PLAIN, 60);
	private Font dateFont = new Font(Font.DIALOG, Font.PLAIN, 20);
	private final int panelMarginHeight = 0;
	private final int panelMarginWidth = 60;
	private final int clockSecondMargin = 20;
	private Dimension clockTextSize;

	//時計の時間
	static int h;
	static int m;
	static int s;
	static int ms;

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
		g.setColor(Color.GREEN);
		g.setFont(clockFont);
		g.drawString(getNowTime(), panelMarginWidth / 2, clockFont.getSize() + panelMarginHeight / 2);
		g.setFont(clockSecondFont);
		g.drawString(getNowSecond(), panelMarginWidth / 2 + (int)clockTextSize.getWidth() + clockSecondMargin, clockFont.getSize() + panelMarginHeight / 2);

		setDigitalLayout(g);

		g.setFont(dateFont);
		g.setColor(new Color(180, 180, 180));
		// 中央に表示するためマジックナンバーで目視調整
		g.drawString(getNowDate(), 185, clockFont.getSize() + dateFont.getSize() + panelMarginHeight + 18);
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
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void setDigitalLayout(Graphics g) {
		g.setColor(Color.BLACK);
		int length = 4;
		int idxX = 0;
		int idxY = 0;
		int xPoints[] = {length / 2, length, length / 2, 0};
		int yPoints[] = {0, length / 2, length, length / 2};

		while (idxY < this.panelSize.height + panelMarginHeight + 13) {
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
