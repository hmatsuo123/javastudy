package ex14;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeFontActionListener implements ItemListener {
	private DigitalClock owner;

	public ChangeFontActionListener(DigitalClock owner) {
		this.owner = owner;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		owner.fontFamily = e.getItem().toString();
		owner.reDisplay();
	}
}
