package puzzle;

import java.util.Date;
import java.util.Random;

public class RotateGridInfo {
	private int intGridXNum;	//マスの横数
	private int intGridYNum;	//マスの縦数
	private double gridFlg[][];	//マス情報
	private final int RADIAN_0 = 0;
	private final int RADIAN_90 = 1;
	private final int RADIAN_180 = 2;
	private final int RADIAN_270 = 3;

	RotateGridInfo(int xNum, int yNum){
		intGridXNum = xNum;
		intGridYNum = yNum;
		gridFlg = new double[intGridXNum][intGridYNum];
		initGridFlg();
	}

	public void initGridFlg() {
		synchronized (gridFlg) {
			// ランダムに回転角度を格納
			Random r = new Random(new Date().getTime());
			for(int y = 0; y < intGridYNum; y++){
				for(int x = 0; x < intGridXNum; x++){
					int random = r.nextInt(4);
					double radian = 0;
					switch(random){
					case RADIAN_0:
						radian = 0;
						break;
					case RADIAN_90:
						radian = Math.PI / 2;
						break;
					case RADIAN_180:
						radian = Math.PI;
						break;
					case RADIAN_270:
						radian = Math.PI * 3 / 2;
						break;
					}
					gridFlg[x][y] = radian;
				}
			}
		}
	}

	public void moveTile(int clickX, int clickY){
		double newRadian = gridFlg[clickX][clickY] + Math.PI / 2;
		gridFlg[clickX][clickY] = newRadian == Math.PI * 2 ? 0 : newRadian;
	}

	public double getTileRadian(int x, int y){
		return gridFlg[x][y];
	}

	public boolean checkGameClear(){
		boolean blnRet;
		blnRet = true;

		for(int y = 0; y < intGridYNum; y++){
			for(int x = 0; x < intGridXNum; x++){
				if(gridFlg[x][y] != 0){
					blnRet = false;
					break;
				}
			}
		}
		return blnRet;
	}
}
