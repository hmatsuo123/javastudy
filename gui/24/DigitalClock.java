package ex24;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DigitalClock extends JFrame {
	private DigitalClock digitalClockFrame;
	private DigitalClockPanel digitalClockPanel;
	private JMenuItem viewSettingsMenuItem;
	private JMenuItem animationSettingsMenuItem;
	private DigitalClockPreferences prefs;

	public DigitalClock() {
		super("Digital Clock");
		this.digitalClockFrame = this;
		digitalClockPanel = DigitalClockPanel.newInstance(this);
		add(digitalClockPanel);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);

		MenuActionListener listener = new MenuActionListener();
		JMenuBar menubar = new JMenuBar();
		JMenu viewMenu = new JMenu("View");
		menubar.add(viewMenu);
		viewSettingsMenuItem = new JMenuItem("Property");
		viewSettingsMenuItem.addActionListener(listener);
		viewMenu.add(viewSettingsMenuItem);
		addWindowListener(new ApplicationCloseAdapter());

		/*JMenu animationMenu = new JMenu("Animation");
		menubar.add(animationMenu);
		animationSettingsMenuItem = new JMenuItem("Settings");
		animationSettingsMenuItem.addActionListener(listener);
		animationMenu.add(animationSettingsMenuItem);*/
		setJMenuBar(menubar);

		// 前回の設定を反映
		prefs = new DigitalClockPreferences();
		Point point = prefs.getFramePoint();
		if (point == null) {
			setLocationRelativeTo(null);
		} else {
			setLocation(point);
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void updateWindow(Dimension d) {
		setSize(d);
		//pack();
	}

	/**
	 * メニュー操作のイベントリスナークラス
	 */
	private class MenuActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source.equals(viewSettingsMenuItem)) {
				new ViewSettingsDialog(digitalClockFrame, digitalClockPanel);
			} else if (source.equals(animationSettingsMenuItem)) {
				new AnimationSettingsDialog(digitalClockFrame, digitalClockPanel);
			}
		}
	}

	private class ApplicationCloseAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
        	prefs.save(digitalClockPanel.clockFont.getName(), String.valueOf(digitalClockPanel.clockFontSize),
        			digitalClockPanel.clockFontColor, digitalClockPanel.clockBackgroundColor, digitalClockFrame.getLocation());
            System.exit(0);
        }
    }

	public static void main(String[] args) {
		new DigitalClock();
	}
}
