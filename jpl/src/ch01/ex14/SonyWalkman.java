package ch01.ex14;


public class SonyWalkman {

	/**
	 * 初代ウォークマン
	 * 1端子で1人で音楽が聴ける
	 * */
	public class Walkman {
		private boolean isUsiongPlugin1 = false;

		/**
		 * 音楽再生スタート
		 * @return boolean true:音楽の再生に成功 false:音楽の再生に失敗
		 * */
		public boolean startMusic() {
			if (!isUsiongPlugin1) {
				isUsiongPlugin1 = true;
				return true;
			}
			return false;
		}
		//音楽再生ストップ
		public void stopMusic() {
			if (isUsiongPlugin1)
				isUsiongPlugin1 = false;
		}
	}

	/**
	 * 第二世代ウォークマン
	 * 2端子で2人同時に音楽が聴ける
	 * */
	public class Walkman2 extends Walkman {
		private boolean isUsiongPlugin2 = false;

		/**
		 * 音楽再生スタート
		 * @return boolean true:音楽の再生に成功 false:音楽の再生に失敗
		 * */
		public boolean startMusic() {
			super.startMusic();

			if (!isUsiongPlugin2) {
				isUsiongPlugin2 = true;
				return true;
			}
			return false;
		}
		//音楽再生ストップ
		public void stopMusic(Object data) {
			super.stopMusic();
			if (isUsiongPlugin2)
				isUsiongPlugin2 = false;
		}
	}

	/**
	 * 第三世代ウォークマン
	 * 2端子で2人同時に音楽が聴け、双方向のコミュニケーションがとれる
	 * */
	public class Walkman3 extends Walkman2 {
		//コミュニケーションを行う
		public void comunication() {
			//コミュニケーション実行処理
		}
	}

}
