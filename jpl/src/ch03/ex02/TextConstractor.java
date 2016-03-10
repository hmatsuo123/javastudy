package ch03.ex02;

public class TextConstractor {
	public static void main(String[] args){
		class X {
			protected int xMask = 0x00ff;
			protected int fullMask;

			public X() {
				System.out.printf("call X constractor   xMask: %x, fullMask: %x\n", xMask, fullMask);
				fullMask = xMask;
				System.out.printf("called X constractor   xMask: %x, fullMask: %x\n", xMask, fullMask);
			}

			public int mask(int orig) {
				return (orig & fullMask);
			}
		}

		class Y extends X {
			protected int yMask = 0xff00;

			public Y() {
				System.out.printf("call Y constractor   xMask: %x, fullMask: %x, yMask: %x\n", xMask, fullMask, yMask);
				fullMask |= yMask;
				System.out.printf("called Y constractor   xMask: %x, fullMask: %x, yMask: %x\n", xMask, fullMask, yMask);
			}
		}

		Y y = new Y();
	}
}
