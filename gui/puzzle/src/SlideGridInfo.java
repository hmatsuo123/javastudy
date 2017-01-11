package puzzle;

import java.util.Date;
import java.util.Random;

public class SlideGridInfo {
	private int intGridXNum;	//マスの横数
	private int intGridYNum;	//マスの縦数
	private int intGridFlg[][];	//マス情報

	SlideGridInfo(int xNum, int yNum){
		intGridXNum = xNum;
		intGridYNum = yNum;
		intGridFlg = new int[intGridXNum][intGridYNum];
		initIntGridFlg();
	}

	public void initIntGridFlg() {
		synchronized (intGridFlg) {
			// 0～15までの数を格納
			for(int y = 0; y < intGridYNum; y++){
				for(int x = 0; x < intGridXNum; x++){
					intGridFlg[x][y] = (y * intGridYNum) + x;
				}
			}

			// 右下のマスにはコマがないことを意味する-1を格納
			intGridFlg[intGridXNum - 1][intGridYNum - 1] = -1;

			// ボードをシャッフル
			Random r = new Random(new Date().getTime());
			for (int i = 0; i < 200; i++) {
				int x = r.nextInt(intGridXNum);
				int x2 = r.nextInt(intGridXNum);
				int y = r.nextInt(intGridYNum);
				int y2 = r.nextInt(intGridYNum);
				int tmp = intGridFlg[x][y];
				intGridFlg[x][y] = intGridFlg[x2][y2];
				intGridFlg[x2][y2] = tmp;
			}
		}
	}

	public boolean moveTile(int clickX, int clickY){
		boolean blnRet;
		boolean blnExist;
		blnRet = true;
		blnExist = false;
		while(true){
			// 右に移動できるか判別
			if(clickX + 1 < intGridXNum && clickX >= 0 && clickY >= 0 && clickY < intGridYNum){
				if(intGridFlg[clickX + 1][clickY] == -1){
					intGridFlg[clickX + 1][clickY] = intGridFlg[clickX][clickY];
					blnExist = true;
					break;
				}
			}
			// 左に移動できるか判別
			if(clickX - 1 >= 0 && clickX < intGridXNum && clickY >= 0 && clickY < intGridYNum){
				if(intGridFlg[clickX - 1][clickY] == -1){
					intGridFlg[clickX - 1][clickY] = intGridFlg[clickX][clickY];
					blnExist = true;
					break;
				}
			}
			// 下に移動できるか判別
			if(clickY + 1 < intGridYNum && clickY >= 0 && clickX >= 0 && clickX < intGridXNum){
				if(intGridFlg[clickX][clickY + 1] == -1){
					intGridFlg[clickX][clickY + 1] = intGridFlg[clickX][clickY];
					blnExist = true;
					break;
				}
			}
			// 上に移動できるか判別
			if(clickY - 1 >= 0 && clickY < intGridYNum && clickX >= 0 && clickX < intGridXNum){
				if(intGridFlg[clickX][clickY - 1] == -1){
					intGridFlg[clickX][clickY - 1] = intGridFlg[clickX][clickY];
					blnExist = true;
					break;
				}
			}
			break;
		}
		// クリックされたマスからコマを移動させたら、そのマスを空にする
		if(blnExist == true){
			intGridFlg[clickX][clickY] = -1;
		}
		return blnRet;
	}
	public int getTileNum(int x, int y){
		return intGridFlg[x][y];
	}
	public boolean checkGameClear(){
		boolean blnRet;
		blnRet = true;

		for(int y = 0; y < intGridYNum; y++){
			for(int x = 0; x < intGridXNum; x++){
				// 右下のマスが空いているかどうかを判断
				if(y == intGridYNum - 1 && x == intGridXNum - 1){
					if(intGridFlg[x][y] != -1){
						blnRet = false;

					}
				}

				// 数字が整列されているかどうかを判断
				else if(intGridFlg[x][y] != (y * intGridYNum) + x){
					blnRet = false;
				}
			}
		}
		return blnRet;
	}
}
