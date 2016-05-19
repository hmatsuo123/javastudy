package ex13;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeFontColorActionListener implements ActionListener {
    private DigitalClock13 owner;

    public ChangeFontColorActionListener(DigitalClock13 owner) {
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	owner.fontColor = owner.colorDecodeMap.get(e.getActionCommand());
		owner.reDisplay();
    }
}
