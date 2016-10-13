package ex23;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JMenuItem;
import javax.swing.JWindow;

public class DigitalClock {
	private DigitalClock digitalClockFrame;
	private DigitalClockPanel digitalClockPanel;
	private JMenuItem viewSettingsMenuItem;
	private JMenuItem animationSettingsMenuItem;
	private int X;
	private int Y;

	public DigitalClock() {
		super();
		this.digitalClockFrame = this;
		digitalClockPanel = DigitalClockPanel.newInstance(this);
		//add(digitalClockPanel);
		//pack();
		//setResizable(true);
		//setLocationRelativeTo(null);

		/*MenuActionListener listener = new MenuActionListener();
		JMenuBar menubar = new JMenuBar();
		JMenu viewMenu = new JMenu("View");
		menubar.add(viewMenu);
		viewSettingsMenuItem = new JMenuItem("Property");
		viewSettingsMenuItem.addActionListener(listener);
		viewMenu.add(viewSettingsMenuItem);

		JMenu animationMenu = new JMenu("Animation");
		menubar.add(animationMenu);
		animationSettingsMenuItem = new JMenuItem("Settings");
		animationSettingsMenuItem.addActionListener(listener);
		animationMenu.add(animationSettingsMenuItem);
		setJMenuBar(menubar);*/

		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.addMouseListener(new ClockMouseAdapter(this));
		//this.addMouseMotionListener(new ClockMouseMotionAdapter());
		//setVisible(true);
	}
/*
	@Override
	public void paint(Graphics g) {
		digitalClockPanel.reDisplay();
	}

	public void SystemExit() {
		dispose();
		System.exit(0);
	}

	public void SetParentLocation(int x, int y) {
		setLocation(x, y);
		System.out.println("Parent X:" + x + ", Y:" + y);
	}

	//マウスクリック、ドラッグイベント
		class ClockMouseAdapter extends MouseAdapter {
			DigitalClock owner;

			public ClockMouseAdapter(DigitalClock clockWindow) {
				this.owner = clockWindow;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				owner.X = e.getX();
				owner.Y = e.getY();
			}
		}

	class ClockMouseMotionAdapter extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			setLocation(getX() + e.getX() -X, getY() + e.getY() - Y);
			//System.out.println("ClockMouseMotionAdapter X:" + X + ", Y:" + Y);
		}
	}*/

	public static void main(String[] args) {
		new DigitalClock();
	}
}
