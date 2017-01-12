package puzzle;

import java.util.Random;

public class FallDownClock {
	private final int width ;
	private final int height;
	private int bufferSize = 20;
	private static final int GRAVITY_VALUE = 2;
	private static final int SHAKE_VALUE = 1;
	private static Random random = new Random();
	public int x = 0;
	public int y = 0;
	public ShowTimeTarget showTime = ShowTimeTarget.SECOND;
	// 1の桁と10の桁のどちらを表示するか
	public boolean is1DigitShow = true;
	public static final int FONTSIZE = 20;

	public enum ShowTimeTarget {
		HOUR,
		MINUTE,
		SECOND,
		COLON
	}

	public FallDownClock(int panelWidth, int panelHeight) {
		width = panelWidth + bufferSize;
		height = panelHeight + bufferSize;
		//x = random.nextInt(PANEL_WIDTH + BUFFER_WIDTH * 2);
		x = random.nextInt(width);
		y = 0;
	}

	public void fall() {
		y += GRAVITY_VALUE;

		// 左右のブレ幅調整
		switch (random.nextInt(3)) {
		case 0:
			x += SHAKE_VALUE;
			break;
		case 1:
			x -= SHAKE_VALUE;
			break;
		default:
			break;
		}
	}

	public boolean isLanding() {
		return y > height;
	}
}
