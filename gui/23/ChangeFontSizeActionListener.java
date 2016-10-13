package ex23;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeFontSizeActionListener implements ActionListener {
    private DigitalClockPanel owner;

    public ChangeFontSizeActionListener(DigitalClockPanel owner) {
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	int fontSize = Integer.parseInt(e.getActionCommand());
    	owner.setClockFont("", fontSize);
		owner.reDisplay();
    }
}
