package ex24;

import java.awt.Color;
import java.awt.Point;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class DigitalClockPreferences {
	private Preferences prefs;
	private static final String fontFamilyKey = "fontFamily";
	private static final String fontSizeKey = "fontSize";
	private static final String fontColorKey = "fontColor";
	private static final String backGroundColorKey = "backGroundColor";
	private static final String framePointXKey = "framePointX";
	private static final String framePointYKey = "framePointY";

	public DigitalClockPreferences() {
		prefs = Preferences.userNodeForPackage(this.getClass());
	}

	public void save(String fontFamily, String fontSize, Color fontColor, Color backGroundColor, Point point) {
		try {
			prefs.put(fontFamilyKey, fontFamily);
			prefs.put(fontSizeKey, fontSize);
			prefs.put(fontColorKey, String.valueOf(fontColor.getRGB()));
			prefs.put(backGroundColorKey, String.valueOf(backGroundColor.getRGB()));
			prefs.put(framePointXKey, String.valueOf(point.getX()));
			prefs.put(framePointYKey, String.valueOf(point.getY()));
			prefs.flush();
		} catch (BackingStoreException ex) {
			ex.printStackTrace();
		}
	}

	public String getFontFamily() {
		return prefs.get(fontFamilyKey, null);
	}

	public String getFontSize() {
		return prefs.get(fontSizeKey, null);
	}

	public String getFontColor() {
		return prefs.get(fontColorKey, null);
	}

	public String getBackGroundColor() {
		return prefs.get(backGroundColorKey, null);
	}

	public Point getFramePoint() {
		String xString = prefs.get(framePointXKey, null);
		String yString = prefs.get(framePointYKey, null);
		if (xString == null && yString == null) {
			return null;
		}
		return new Point((int)Double.parseDouble(xString), (int)Double.parseDouble(yString));
	}
}
