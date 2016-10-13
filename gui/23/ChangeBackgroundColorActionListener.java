package ex23;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

public class ChangeBackgroundColorActionListener implements ActionListener {
    private DigitalClockPanel owner;

    public ChangeBackgroundColorActionListener(DigitalClockPanel owner) {
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	JColorChooser colorchooser = new JColorChooser();
		Color selectedColor = JColorChooser.showDialog(owner, "Select Font Color", Color.white);
    	owner.setBackgroundColor(selectedColor);
		owner.reDisplay();
    }
}
