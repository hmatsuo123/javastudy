package ch06.ex05;


public class SignalColorEnumMethod {
	enum Signal {
		STOP("red") {
			String getColor() {
				return color;
			}
		},
		careful("yellow") {
			String getColor() {
				return color;
			}
		},
		GO("blue") {
			String getColor() {
				return color;
			}
		};

		String color;
		Signal(String color) {
			this.color = color;
		}

		/*
		 * 【回答】
		 * 今回のケースのように色情報のみ取得するだけの処理を実装するのであれば、
		 * 練習問題3.4のようにカラーオブジェクトを持つようにすれば良いので定数固有メソッドを使用する必要はない
		 * */
		abstract String getColor();
	}

	public static void main(String[] args) {
		for (Signal signal : Signal.values()) {
			System.out.println(signal + ": " + signal.getColor());
		}
	}
}
