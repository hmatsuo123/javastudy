package ex12;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class DigitalClock12 extends Frame implements ActionListener {
	static int h;
    static int m;
    static int s;

    public int fontSize = 30;

    enum MenuContents {
    	FILE("File"),
    	PROPERTY("Property"),
    	EXIT("Exit"),
    	FONT("Font"),
    	BACKGROUNDCOLOR("Background Color");

    	//UIに表示する文字
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
        setSize(300, 200);

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

        setVisible(true);

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

		} else if (e.getActionCommand() == MenuContents.EXIT.getText()) {
			dispose();
    		System.exit(0);
		}
    }

    @Override
    public void paint(Graphics g) {
    	Graphics2D g2d = (Graphics2D)g;
    	Font font = new Font("Arial", Font.BOLD, fontSize);
    	g2d.setFont(font);

    	g2d.drawString(h + ":" + m + ":" + s, 80, 100);
    }

    public void startClock() {
    	Thread th = new Thread();
        Calendar now = Calendar.getInstance();

        while(true){
            h = now.getInstance().get(now.HOUR_OF_DAY);
            m = now.getInstance().get(now.MINUTE);
            s= now.getInstance().get(now.SECOND);

            this.repaint();
            try{
            	//1秒スリープ
                th.sleep(1000);
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
