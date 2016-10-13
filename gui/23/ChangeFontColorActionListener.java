package ex23;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

public class ChangeFontColorActionListener implements ActionListener {
    private DigitalClockPanel owner;

    public ChangeFontColorActionListener(DigitalClockPanel owner) {
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	JColorChooser colorchooser = new JColorChooser();
		Color selectedFontColor = JColorChooser.showDialog(owner, "Select Font Color", Color.white);
    	owner.setClockFontColer(selectedFontColor);
		owner.reDisplay();
    }
}
