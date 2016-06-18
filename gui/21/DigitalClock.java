package ex21;

import javax.swing.JFrame;

public class DigitalClock extends JFrame {
	public DigitalClock() {
		super("Digital Clock");
		//setLayout(new BorderLayout());
		DigitalClockPanel panel = DigitalClockPanel.newInstance();
		add(panel);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);

		//setLayout(null);
		//setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new DigitalClock();
	}
}
