package puzzle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puzzle.DigitalClockPanel.IClockEventListener;

public class SlidePuzzlePanel extends JPanel implements IClockEventListener{
	private final Dimension panelSize = new Dimension();
	private DigitalClockPanel clockPanel;
	private SlidePuzzlePanel slidePuzzlePanel;

	private final int GAME_WAIT = 0;		//ゲーム状態フラグ定数（タイトル画面時）
	private final int GAME_PLAYING = 1;		//ゲーム状態フラグ定数（ゲーム中)
	private final int GRID_X;        		//ボードの横マス数
	private final int GRID_Y;        		//ボードの縦マス数
	private final int GRID_WIDTH;    		//マスの横幅
	private final int GRID_HEIGHT;    		//マスの縦幅
	private int gameStatus;					//ゲーム状態フラグ
	private SlideGridInfo GInfo;					//グリッドクラス
	public boolean isHintShow = false;

	public SlidePuzzlePanel(int panelWidth, int panelHeight, int intGridXNum, int intGridYNum, DigitalClockPanel panel) {
		super();
		slidePuzzlePanel = this;
		// ダブルバッファリングを行うとなぜかちらついてしまう
		setDoubleBuffered(false);
		panelSize.width = panelWidth;
		panelSize.height = panelHeight;
		GRID_X = intGridXNum;
		GRID_Y = intGridYNum;
		GRID_WIDTH = panelWidth / intGridXNum;
		GRID_HEIGHT = panelHeight / intGridYNum;

		clockPanel = panel;
		setBackground(Color.WHITE);
		GInfo = new SlideGridInfo(GRID_X, GRID_Y);
		gameStatus = GAME_PLAYING;

		addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				int clickTileX;
				int clickTileY;
				boolean blnRet;
				/* クリック座標からクリックされたコマが置いてあるマスを取得 */
				clickTileX = (int)(e.getX() / GRID_WIDTH);
				clickTileY = (int)(e.getY() / GRID_HEIGHT);
				/*コマを移動させる。移動できない場合は何もしない */
				blnRet = GInfo.moveTile(clickTileX, clickTileY);
				/* コマが整列した場合はゲーム終了 */
				blnRet = GInfo.checkGameClear();
				if(blnRet == true){
					/* ゲーム状態フラグを変更 */
					gameStatus = GAME_WAIT;
				}
				slidePuzzlePanel.repaint();
			}
		});

		clockPanel.setListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
		g.setColor(Color.WHITE);

		BufferedImage image = clockPanel.getImage();
		for(int y = 0; y < GRID_Y; y++){
			for(int x = 0; x < GRID_X; x++){
				if(GInfo.getTileNum(x, y) != -1){
					int idx = GInfo.getTileNum(x, y);
					int col = idx % GRID_X;
					int row = (int)idx / GRID_X;
					g.drawImage(image, x * GRID_WIDTH, y * GRID_HEIGHT, x * GRID_WIDTH + GRID_WIDTH, y * GRID_HEIGHT + GRID_HEIGHT,
							col * GRID_WIDTH, row * GRID_HEIGHT, col * GRID_WIDTH + GRID_WIDTH, row * GRID_HEIGHT + GRID_HEIGHT, this);

					if (isHintShow)
						g.drawString(String.valueOf(idx), x * GRID_WIDTH + 5, y * GRID_HEIGHT + 12);
				}
			}
		}

		switch(gameStatus){
		case GAME_WAIT:
			gameStatus = GAME_PLAYING;
			// ダイアログを表示
			String crlf = System.getProperty("line.separator");
			int option = JOptionPane.showConfirmDialog(this, "Game clear!" + crlf + "Would you like to restart the game?", "Congratulations", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (option == JOptionPane.YES_OPTION){
				GInfo.initIntGridFlg();
			    }
			break;
		case GAME_PLAYING:
			break;
		}
	};

	@Override
	public Dimension getPreferredSize() {
		return panelSize;
	}

	/**
	 * 時計が更新された際に実行されるイベントハンドラ
	 * 最新の時計のキャプチャ画像を取得後、tileImageを更新し、再描画する
	 */
	@Override
	public void ClockUpdated() {
		this.repaint();
	}
}
