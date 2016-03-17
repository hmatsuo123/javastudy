package ch03.ex07;

public class AttrOverRide {
	public class Attr {
		private final String name;
		private Object value = null;

		public Attr(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public Object getValue() {
			return value;
		}

		public Object setValue(Object newValue) {
			Object oldVal = value;
			value = newValue;
			return oldVal;
		}

		public String toString() {
			return name + "='" + value + "'";
		}

		public boolean equals(Attr o){
			if((o.getName() == this.name) && (o.getValue() == this.value)){
				return true ;
			}
			else{
				return false ;
			}
		}

		//TODO:hasCodeのオーバーライドは未実施
	}

}
