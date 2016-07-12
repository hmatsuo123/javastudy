package interpret;

import javax.swing.JFrame;

public class Interpret extends JFrame {
	public Interpret() {
		super("Interpret");
		InterpretPanel panel = new InterpretPanel(this);
		add(panel);
		pack();

		//setLayout(null);
		//setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Interpret();
	}
}
