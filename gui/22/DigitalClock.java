package ex22;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DigitalClock extends JFrame {
	private DigitalClock digitalClockFrame;
	private DigitalClockPanel digitalClockPanel;
	private JMenuItem viewSettingsMenuItem;
	private JMenuItem animationSettingsMenuItem;

	public DigitalClock() {
		super("Digital Clock");
		this.digitalClockFrame = this;
		digitalClockPanel = DigitalClockPanel.newInstance();
		add(digitalClockPanel);
		pack();
		setResizable(true);
		setLocationRelativeTo(null);

		MenuActionListener listener = new MenuActionListener();
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
		setJMenuBar(menubar);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
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

	public static void main(String[] args) {
		new DigitalClock();
	}
}
