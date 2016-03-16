package ch03.ex05;

public class ExtendBenchmarkClass {
	public static void main(String[] args) {
		abstract class Benchmark {
			abstract void benchmark();

			public final long repeat(int count) {
				long start = System.nanoTime();
				for (int i = 0; i < count; i++)
					benchmark();
				return (System.nanoTime() - start);
			}
		}

		class MethodBenckmark extends Benchmark {
			protected int roopCount = 0;

			void benchmark() {
				for (int i = 0; i < roopCount; i++) {

				}
			}

			protected void setRoopCount(int count) {
				this.roopCount = count;
			}
		}

		int count = Integer.parseInt(args[0]);
		MethodBenckmark methodBenckmark = new MethodBenckmark();
		methodBenckmark.setRoopCount(count);
		long time = methodBenckmark.repeat(count);
		System.out.println(count + " methods in " + time + " nanoseconds");
	}

}
