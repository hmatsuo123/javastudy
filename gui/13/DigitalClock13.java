package ex13;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DigitalClock13 extends Window implements ActionListener {
	//時計の時間
	private static int h;
	private static int m;
	private static int s;
	private static int ms;

	//フォントサイズ
	private static final int fontSizeSmall = 10;
	private static final int fontSizeBig = 30;
	private static final int fontSizeLarge = 60;

	//フォント表示位置
	private int fontPositionX = 30;
	private int fontPositionY = 50;

	//カラー
	public Map<Color, String> colorMap = new HashMap<Color, String>();
	public Map<String, Color> colorDecodeMap = new HashMap<String, Color>();

	public int frameSizeWidth = 1000;
	public int frameSizeHeigth = 700;
	public int fontSize = fontSizeBig;
	public String fontFamily = Font.DIALOG;
	public Color fontColor = Color.GREEN;
	public Color backgroundColor = Color.BLACK;

	Graphics gBuf;
	Image imgBuf;

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

	DigitalClock13(String frameTitle) {
		super(null, null);
		setSize(frameSizeWidth, frameSizeHeigth);
		initColorMap();

		PopupMenu popupMenu = new PopupMenu();

		//[Font]
		Menu fontMenu = new Menu(MenuContents.FONT.getText());
		popupMenu.add(fontMenu);
		//[Font]-[etc...]
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		for (Font f : ge.getAllFonts()) {
			MenuItem item = new MenuItem(f.getName());
			item.addActionListener(new ChangeFontActionListener(this));
			fontMenu.add(item);
		}

		//[Font Size]
		Menu fontSizeMenu = new Menu(MenuContents.FONTSIZE.getText());
		popupMenu.add(fontSizeMenu);

		//[Font Size]-[etc...]
		ChangeFontSizeActionListener fontSizeListener = new ChangeFontSizeActionListener(this);
		MenuItem itemSmall = new MenuItem(Integer.toString(fontSizeSmall));
		itemSmall.addActionListener(fontSizeListener);
		fontSizeMenu.add(itemSmall);
		MenuItem itemBig = new MenuItem(Integer.toString(fontSizeBig));
		itemBig.addActionListener(fontSizeListener);
		fontSizeMenu.add(itemBig);
		MenuItem itemLarge = new MenuItem(Integer.toString(fontSizeLarge));
		itemLarge.addActionListener(fontSizeListener);
		fontSizeMenu.add(itemLarge);

		//[Font Color]
		Menu fontColorMenu = new Menu(MenuContents.FONTCOLOR.getText());
		popupMenu.add(fontColorMenu);

		//[Font Color]-[etc...]
		for (Color color : colorMap.keySet()) {
			MenuItem item = new MenuItem(colorMap.get(color));
			item.addActionListener(new ChangeFontColorActionListener(this));
			fontColorMenu.add(item);
		}

		//[Background Color]
		Menu backgroundColorMenu = new Menu(MenuContents.BACKGROUNDCOLOR.getText());
		popupMenu.add(backgroundColorMenu);

		//[Background color]-[etc...]
		for (Color color : colorMap.keySet()) {
			MenuItem item = new MenuItem(colorMap.get(color));
			item.addActionListener(new ChangeBackgroundColorActionListener(this));
			backgroundColorMenu.add(item);
		}

		//[----]
		popupMenu.addSeparator();

		//[Exit]
		MenuItem exitMenu = new MenuItem(MenuContents.EXIT.getText());
		exitMenu.addActionListener(this);
		popupMenu.add(exitMenu);

		this.add(popupMenu);
		this.addMouseListener(new ClockMouseAdapter(this, popupMenu));
		this.addMouseMotionListener(new ClockMouseMotionAdapter());

		setVisible(true);

		//コンポーネント表示後にイメージを取得する
		imgBuf = createImage(frameSizeWidth, frameSizeHeigth);
		gBuf = imgBuf.getGraphics();
	}

	private void initColorMap() {
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
	}

	@Override
	//メニュー選択時の操作
	public void actionPerformed(ActionEvent e) {
		//ウィンドウを閉じる
		if (e.getActionCommand() == MenuContents.EXIT.getText()) {
			dispose();
			System.exit(0);
		}
	}

	//マウスクリック、ドラッグイベント
	class ClockMouseAdapter extends MouseAdapter {
		DigitalClock13 owner;
		PopupMenu popupMenu;

		public ClockMouseAdapter(DigitalClock13 clockWindow, PopupMenu popupMenu) {
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
			setLocation(getX() + e.getX() -X, getY() + e.getY() - Y);
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

		contentReSize();

		String time = String.format("%02d:%02d:%02d:%03d", h, m, s, ms);
		g2d.drawString(time, fontPositionX, fontPositionY);

		setSize(frameSizeWidth, frameSizeHeigth);
	}

	private void contentReSize() {
		if (fontSize == fontSizeSmall) {
			fontPositionX = 10;
			fontPositionY =18;

			frameSizeWidth = 100;
			frameSizeHeigth = 30;
		} else if (fontSize == fontSizeBig) {
			fontPositionX = 30;
			fontPositionY =45;

			frameSizeWidth = 280;
			frameSizeHeigth = 70;
		} else if (fontSize == fontSizeLarge) {
			fontPositionX = 30;
			fontPositionY =70;

			frameSizeWidth = 480;
			frameSizeHeigth = 100;
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
		DigitalClock13 clockFrame = new DigitalClock13("DigitalClock");
		clockFrame.startClock();
	}
}