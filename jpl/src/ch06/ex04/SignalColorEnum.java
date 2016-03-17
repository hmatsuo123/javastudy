package ch06.ex04;

public class SignalColorEnum {
	enum Signal {
		STOP("red"),
		careful("yellow"),
		GO("blue");

		String color;
		Signal(String color) {
			this.color = color;
		}
		public String getColor() {
			return color;
		}
	}

	public static void main(String[] args) {
		for (Signal signal : Signal.values()) {
			System.out.println(signal + ": " + signal.getColor());
		}
	}
}
