package ex14;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeBackgroundColorActionListener implements ItemListener {
	private DigitalClock owner;

	public ChangeBackgroundColorActionListener(DigitalClock owner) {
		this.owner = owner;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		owner.backgroundColor = owner.colorDecodeMap.get(e.getItem().toString());
		owner.reDisplay();
	}
}
