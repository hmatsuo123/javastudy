package ex14;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeFontSizeActionListener implements ItemListener {
	private DigitalClock owner;

	public ChangeFontSizeActionListener(DigitalClock owner) {
		this.owner = owner;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		owner.fontSize = PropertyDialog.getFontSize(e.getItem().toString());
		owner.reDisplay();
	}
}
