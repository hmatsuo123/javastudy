package puzzle;

import java.awt.Color;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class DigitalClockPreferences {
	private Preferences prefs;
	private static final String fontFamilyKey = "fontFamily";
	private static final String fontColorKey = "fontColor";

	public DigitalClockPreferences() {
		prefs = Preferences.userNodeForPackage(this.getClass());
	}

	public void save(String fontFamily, Color fontColor) {
		try {
			prefs.put(fontFamilyKey, fontFamily);
			prefs.put(fontColorKey, String.valueOf(fontColor.getRGB()));
			prefs.flush();
		} catch (BackingStoreException ex) {
			ex.printStackTrace();
		}
	}

	public String getFontFamily() {
		return prefs.get(fontFamilyKey, null);
	}

	public String getFontColor() {
		return prefs.get(fontColorKey, null);
	}
}
