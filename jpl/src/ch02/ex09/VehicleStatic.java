package ch02.ex09;

import ch02.ex01.Vehicle;

public class VehicleStatic extends Vehicle {
	public int uniquID;
	static int maxUniqueID;

	static public int getMaxUniqueID() {
		return maxUniqueID;
	}

	static public void setUniqueID(int id) {
		if (maxUniqueID < id)
			maxUniqueID = id;
	}

}
