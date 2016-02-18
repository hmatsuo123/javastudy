package ch02.ex13;


public class VehicleAccessor {
		private double speed;
		private double direction;
		private String owner;

		public double getSpeed() {
			return this.speed;
		}
		public double getDirection() {
			return this.direction;
		}
		public String getOwner() {
			return this.owner;
		}

		//速度、角度、所有者は変更する可能性があるため許可するが、車体番号などの不変フィールドがある場合許可しない
		public void setSpeed(double speed) {
			this.speed = speed;
		}
		public void setDirectiond(double direction) {
			this.direction = direction;
		}
		public void setOwner(String owner) {
			this.owner = owner;
		}
}
