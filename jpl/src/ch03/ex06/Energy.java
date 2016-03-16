package ch03.ex06;

public class Energy {
	public class Vehicle {
		public double speed;
		public double direction;
		public String owner;
		public GasTank gasTunk;
		public Battery battery;


		public Vehicle() {
			gasTunk = new GasTank();
			battery = new Battery();
		}

		public void start() {
			gasTunk.setGas(20);
			battery.setElectrical(20);
		}
	}

	public Vehicle getVehicle() {
		return new Vehicle();
	}

	abstract class EnergySourse {
		/** 動力源が空であるかチェックするメソッド
		 *  @Return boolena ture: 空、 false: 空でない
		*/
		abstract boolean empty();
	}

	public class GasTank extends EnergySourse {
		//ガスタンクの残量
		public int gasAmount = 0;
		public int maxGasAmount = 1000;

		boolean empty() {
			return (gasAmount > 0) ? true : false;
		}

		//ガスを充電する
		public void setGas(int gasAmount){
			int sum = this.gasAmount + gasAmount;
			if(sum < maxGasAmount) {
				this.gasAmount += gasAmount;
			} else {
				this.gasAmount = maxGasAmount;
			}
		}

		//ガスを使用する
		public void useGas(int gasAmount){
			int sum = this.gasAmount - gasAmount;
			if(sum > 0) {
				this.gasAmount -= gasAmount;
			} else {
				this.gasAmount = 0;
			}
		}
	}

	public class Battery extends EnergySourse {
		//バッテリーの残量
		public int electrialamount = 0;
		public int maxElectrialamount = 1000;

		boolean empty() {
			return (electrialamount > 0) ? true : false;
		}

		//電気を充電する
		public void setElectrical(int electrialamount){
			int sum = this.electrialamount + electrialamount;
			if(sum < maxElectrialamount) {
				this.electrialamount += electrialamount;
			} else {
				this.electrialamount = maxElectrialamount;
			}
		}

		//電気を使用する
		public void useElectrical(int electrialamount){
			int sum = this.electrialamount - electrialamount;
			if(sum > 0) {
				this.electrialamount -= electrialamount;
			} else {
				this.electrialamount = 0;
			}
		}
	}

}
