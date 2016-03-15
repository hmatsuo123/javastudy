package ex11;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class DigitalClock11 extends Frame {
	static int h;
    static int m;
    static int s;

    DigitalClock11(String frameTitle) {
        super(frameTitle);
        setSize(300, 200);
        show();

        //ウィンドウを閉じる
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent event) {
        		dispose();
        		System.exit(0);
        	}
        });
    }

    public void paint(Graphics g) {
    	Graphics2D g2d = (Graphics2D)g;
    	Font font = new Font("Arial", Font.BOLD, 30);
    	g2d.setFont(font);

    	g2d.drawString(h + ":" + m + ":" + s, 80, 100);
    }

    public static void main(String [] args) {
    	DigitalClock11 clockFrame = new DigitalClock11("DigitalClock");
        Thread th = new Thread();
        Calendar now = Calendar.getInstance();

        while(true){
            h = now.getInstance().get(now.HOUR_OF_DAY);
            m = now.getInstance().get(now.MINUTE);
            s= now.getInstance().get(now.SECOND);

            clockFrame.repaint();
            try{
            	//1秒スリープ
                th.sleep(1000);
            }catch(InterruptedException e){
            }
        }
    }

}