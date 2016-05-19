package ex13;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeFontActionListener implements ActionListener {
    private DigitalClock13 owner;

    public ChangeFontActionListener(DigitalClock13 owner) {
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	owner.fontFamily = e.getActionCommand();
		owner.reDisplay();
    }
}
