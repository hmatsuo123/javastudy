package ex14;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class DigitalClock extends Frame implements ActionListener{
	//時計の時間
	static int h;
	static int m;
	static int s;
	static int ms;

	public int frameSizeWidth = 1000;
	public int frameSizeHeigth = 700;
	public int fontSize = 30;
	public String fontFamily = Font.DIALOG;
	public Color fontColor = Color.GREEN;
	public Color backgroundColor = Color.BLACK;
	public Map<Color, String> colorMap = new HashMap<Color, String>();
	public Map<String, Color> colorDecodeMap = new HashMap<String, Color>();

	//フォント表示位置
	private int fontPositionX = 30;
	private int fontPositionY = 50;

	DigitalClockPreferences prefs;

	Graphics gBuf;
	Image imgBuf;

	enum MenuContents {
		FILE("File"),
		PROPERTY("Property"),
		EXIT("Exit");

		//UI上に表示する文字
		String text;

		MenuContents(String text) {
			this.text = text;
		}

		String getText() {
			return text;
		}
	}

	DigitalClock(String frameTitle) {
		super(frameTitle);
		setSize(frameSizeWidth, frameSizeHeigth);

		// カラーマップの初期化
		colorMap.put(Color.BLACK, "BLACK");
		colorMap.put(Color.BLUE, "BLUE");
		colorMap.put(Color.CYAN, "CYAN");
		colorMap.put(Color.DARK_GRAY, "DARK_GRAY");
		colorMap.put(Color.GRAY, "GRAY");
		colorMap.put(Color.GREEN, "GREEN");
		colorMap.put(Color.LIGHT_GRAY, "LIGHT_GRAY");
		colorMap.put(Color.MAGENTA, "MAGENTA");
		colorMap.put(Color.ORANGE, "ORANGE");
		colorMap.put(Color.PINK, "PINK");
		colorMap.put(Color.RED, "RED");
		colorMap.put(Color.WHITE, "WHITE");
		colorMap.put(Color.YELLOW, "YELLOW");

		colorDecodeMap.put("BLACK", Color.BLACK);
		colorDecodeMap.put("BLUE", Color.BLUE);
		colorDecodeMap.put("CYAN", Color.CYAN);
		colorDecodeMap.put("DARK_GRAY", Color.DARK_GRAY);
		colorDecodeMap.put("GRAY", Color.GRAY);
		colorDecodeMap.put("GREEN", Color.GREEN);
		colorDecodeMap.put("LIGHT_GRAY", Color.LIGHT_GRAY);
		colorDecodeMap.put("MAGENTA", Color.MAGENTA);
		colorDecodeMap.put("ORANGE", Color.ORANGE);
		colorDecodeMap.put("PINK", Color.PINK);
		colorDecodeMap.put("RED", Color.RED);
		colorDecodeMap.put("WHITE", Color.WHITE);
		colorDecodeMap.put("YELLOW", Color.YELLOW);

		MenuBar menuBar = new MenuBar();
		setMenuBar(menuBar);
		// [File]
		Menu menuFile = new Menu(MenuContents.FILE.getText());
		menuFile.addActionListener(this);
		menuBar.add(menuFile);
		// [File]-[Property]
		MenuItem menuProperty = new MenuItem(MenuContents.PROPERTY.getText());
		menuProperty.addActionListener(this);
		menuFile.add(menuProperty);
		// [File]-[----]
		menuFile.addSeparator();
		// [File]-[Exit]
		MenuItem menuExit = new MenuItem("Exit");
		menuFile.add(menuExit);

		// 前回の設定を反映
		prefs = new DigitalClockPreferences();
		if (prefs.getFontFamily() != null) {
			fontFamily = prefs.getFontFamily();
		}
		if (prefs.getFontSize() != null) {
			fontSize = Integer.parseInt(prefs.getFontSize());
		}
		if (prefs.getFontColor() != null) {
			fontColor = colorDecodeMap.get(prefs.getFontColor());
		}
		if (prefs.getBackGroundColor() != null) {
			backgroundColor = colorDecodeMap.get(prefs.getBackGroundColor());
		}

		Point point = prefs.getFramePoint();
		if (point == null) {
			setLocationRelativeTo(null);
		} else {
			setLocation(point);
		}

		setVisible(true);

		//コンポーネント表示後にイメージを取得する
		imgBuf = createImage(frameSizeWidth, frameSizeHeigth);
		gBuf = imgBuf.getGraphics();

		//ウィンドウを閉じる
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				DigitalClock frame = (DigitalClock)event.getSource();
				prefs.save(fontFamily, String.valueOf(fontSize), colorMap.get(fontColor), colorMap.get(backgroundColor), frame.getLocation());
				dispose();
				System.exit(0);
			}
		});
	}

	@Override
	//メニュー選択時の操作
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == MenuContents.PROPERTY.getText()) {
			System.out.println(e.getActionCommand());
			PropertyDialog propertyDialog = new PropertyDialog(this, fontSize, fontFamily, fontColor, backgroundColor);
			propertyDialog.setVisible(true);
		} else if (e.getActionCommand() == MenuContents.EXIT.getText()) {
			prefs.save(fontFamily, String.valueOf(fontSize), colorMap.get(fontColor), colorMap.get(backgroundColor), this.getLocation());
			dispose();
			System.exit(0);
		}
	}

	@Override
	public void paint(Graphics g) {
		//バッファを画面に描画
		g.drawImage(imgBuf, 0, 0, this);
	}

	private void drawBufImage() {
		//イメージ画像を再度生成する
		imgBuf = createImage(frameSizeWidth, frameSizeHeigth);
		gBuf = imgBuf.getGraphics();

		gBuf.setColor(backgroundColor);
		gBuf.fillRect(0, 0, frameSizeWidth, frameSizeHeigth);

		gBuf.setColor(fontColor);
		Graphics2D g2d = (Graphics2D)gBuf;
		Font font = new Font(fontFamily, Font.BOLD, fontSize);
		g2d.setFont(font);

		String time = String.format("%02d:%02d:%02d:%03d", h, m, s, ms);

		/*frameSizeWidth = 50 + fontSize * 10;
    	frameSizeHeigth = 100 + fontSize;
		g2d.drawString(time, 50, 100);*/

		contentReSize();
		g2d.drawString(time, fontPositionX, fontPositionY);
		setSize(frameSizeWidth, frameSizeHeigth);
	}

	private void contentReSize() {
		if (fontSize == PropertyDialog.FontSizeConstant.SMALL.getSize()) {
			fontPositionX = 25;
			fontPositionY = 75;

			frameSizeWidth = 80;
			frameSizeHeigth = 100;
		} else if (fontSize == PropertyDialog.FontSizeConstant.BIG.getSize()) {
			fontPositionX = 35;
			fontPositionY = 95;

			frameSizeWidth = 290;
			frameSizeHeigth = 125;
		} else if (fontSize == PropertyDialog.FontSizeConstant.LARGE.getSize()) {
			fontPositionX = 40;
			fontPositionY =120;

			frameSizeWidth = 510;
			frameSizeHeigth = 160;
		}
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	public void startClock() {
		Thread th = new Thread();
		Calendar now = Calendar.getInstance();

		while(true){
			h = now.getInstance().get(now.HOUR_OF_DAY);
			m = now.getInstance().get(now.MINUTE);
			s= now.getInstance().get(now.SECOND);
			ms= now.getInstance().get(now.MILLISECOND);

			drawBufImage();
			repaint();
			try{
				//10msスリープ
				th.sleep(10);
			}catch(InterruptedException e){
			}
		}
	}

	public void reDisplay() {

		this.repaint();
	}

	public static void main(String [] args) {
		DigitalClock clockFrame = new DigitalClock("DigitalClock");
		clockFrame.startClock();
	}

	// 設定を保持するクラス
	public class DigitalClockPreferences {
		private Preferences prefs;
		private static final String fontFamilyKey = "fontFamily";
		private static final String fontSizeKey = "fontSize";
		private static final String fontColorKey = "fontColor";
		private static final String backGroundColorKey = "backGroundColor";
		private static final String framePointXKey = "framePointX";
		private static final String framePointYKey = "framePointY";

		public DigitalClockPreferences() {
			prefs = Preferences.userNodeForPackage(this.getClass());
		}

		public void save(String fontFamily, String fontSize, String fontColor, String backGroundColor, Point point) {
			try {
				prefs.put(fontFamilyKey, fontFamily);
				prefs.put(fontSizeKey, fontSize);
				prefs.put(fontColorKey, fontColor);
				prefs.put(backGroundColorKey, backGroundColor);
				prefs.put(framePointXKey, String.valueOf(point.getX()));
				prefs.put(framePointYKey, String.valueOf(point.getY()));
				prefs.flush();
			} catch (BackingStoreException ex) {
				ex.printStackTrace();
			}
		}

		public String getFontFamily() {
			return prefs.get(fontFamilyKey, null);
		}

		public String getFontSize() {
			return prefs.get(fontSizeKey, null);
		}

		public String getFontColor() {
			return prefs.get(fontColorKey, null);
		}

		public String getBackGroundColor() {
			return prefs.get(backGroundColorKey, null);
		}

		public Point getFramePoint() {
			String xString = prefs.get(framePointXKey, null);
			String yString = prefs.get(framePointYKey, null);
			if (xString == null && yString == null) {
				return null;
			}
			return new Point((int)Double.parseDouble(xString), (int)Double.parseDouble(yString));
		}
	}
}
