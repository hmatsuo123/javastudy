package ex23;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeFontActionListener implements ActionListener {
    private DigitalClockPanel owner;

    public ChangeFontActionListener(DigitalClockPanel owner) {
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	String fontFamily = e.getActionCommand();
    	owner.setClockFont(fontFamily, 0);
		owner.reDisplay();
    }
}
