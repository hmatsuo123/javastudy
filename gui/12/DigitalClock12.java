package ex12;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class DigitalClock12 extends Frame implements ActionListener {
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

    Graphics gBuf;
    Image imgBuf;

    enum MenuContents {
    	FILE("File"),
    	PROPERTY("Property"),
    	EXIT("Exit"),
    	FONT("Font"),
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

    DigitalClock12(String frameTitle) {
        super(frameTitle);
        setSize(frameSizeWidth, frameSizeHeigth);

        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);
        // [File]
        Menu menuFile = new Menu(MenuContents.FILE.getText());
        menuFile.addActionListener(this);
        menuBar.add(menuFile);
        // [File]-[Property]
        Menu menuProperty = new Menu(MenuContents.PROPERTY.getText());
        menuProperty.addActionListener(this);
        menuFile.add(menuProperty);
        // [View]-[Property]-[Font]
        MenuItem menuSettingFont = new MenuItem(MenuContents.FONT.getText());
        menuProperty.add(menuSettingFont);
        // [View]-[Property]-[Background Color]
        MenuItem menuSettingBackgroundColor = new MenuItem(MenuContents.BACKGROUNDCOLOR.getText());
        menuProperty.add(menuSettingBackgroundColor);
        // [File]-[----]
        menuFile.addSeparator();
        // [File]-[Exit]
        MenuItem menuExit = new MenuItem("Exit");
        menuFile.add(menuExit);

        //this.add(new DigitalClockCanvas());

        setVisible(true);

        //コンポーネント表示後にイメージを取得する
        imgBuf = createImage(frameSizeWidth, frameSizeHeigth);
        gBuf = imgBuf.getGraphics();

        //ウィンドウを閉じる
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent event) {
        		dispose();
        		System.exit(0);
        	}
        });
    }

    @Override
    //メニュー選択時の操作
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == MenuContents.FONT.getText()) {
			System.out.println(e.getActionCommand());
			ChangeFontDialog changeFontDialog = new ChangeFontDialog(this);
			changeFontDialog.setVisible(true);
		} else if (e.getActionCommand() == MenuContents.BACKGROUNDCOLOR.getText()) {
			ChangeBackgroundColor changeBackgroundColor = new ChangeBackgroundColor(this);
			changeBackgroundColor.setVisible(true);
		} else if (e.getActionCommand() == MenuContents.EXIT.getText()) {
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
    	g2d.drawString(time, 50, 100);

    	//TODO:ウィンドウのサイズ調整を修正する(表示位置が適当)
    	frameSizeWidth = 50 + fontSize * 10;
    	frameSizeHeigth = 100 + fontSize;
    	setSize(frameSizeWidth, frameSizeHeigth);
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
    	DigitalClock12 clockFrame = new DigitalClock12("DigitalClock");
    	clockFrame.startClock();
    }
}