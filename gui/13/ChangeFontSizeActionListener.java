package ex13;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeFontSizeActionListener implements ActionListener {
    private DigitalClock13 owner;

    public ChangeFontSizeActionListener(DigitalClock13 owner) {
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	owner.fontSize = Integer.parseInt(e.getActionCommand());
		owner.reDisplay();
    }
}
