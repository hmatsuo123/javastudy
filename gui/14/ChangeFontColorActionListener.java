package ex14;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeFontColorActionListener implements ItemListener {
	private DigitalClock owner;

	public ChangeFontColorActionListener(DigitalClock owner) {
		this.owner = owner;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		owner.fontColor = owner.colorDecodeMap.get(e.getItem().toString());
		owner.reDisplay();
	}
}
