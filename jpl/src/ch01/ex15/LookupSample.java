package ch01.ex15;


public class LookupSample {

	interface Lookup {
		/**
		 * nameと関連づけされた値を返す
		 * そのような値がなければnullを返す
		 * */
		Object find(String name);

		void add(String name, Object value);

		void remove(String name);
	}

	public class SimpleLookup implements Lookup {
		private String[] names = new String[10];
		private Object[] values = new Object[10];
		private int index = 0;

		public Object find(String name) {
			for (int i = 0; i < names.length; i++) {
				if (names[i] != null && names[i].equals(name))
					return values[i];
			}
			return null;
		}

		public void add(String name, Object value) {
			names[index] = name;
			values[index] = value;
			index++;
		}

		public void remove(String name) {
			for (int i = 0; i < names.length; i++) {
				if (names[i] != null && names[i].equals(name)) {
					names[i] = null;
					values[i] = null;
				}
			}
		}
	}

	//テストコードのために追加
	public SimpleLookup getSimpleLookup() {
		return new SimpleLookup();
	}

}
