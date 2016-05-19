package ex13;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeBackgroundColorActionListener implements ActionListener {
    private DigitalClock13 owner;

    public ChangeBackgroundColorActionListener(DigitalClock13 owner) {
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	owner.backgroundColor = owner.colorDecodeMap.get(e.getActionCommand());
		owner.reDisplay();
    }
}
