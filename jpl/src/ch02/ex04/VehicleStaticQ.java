package ch02.ex04;

public class VehicleStaticQ {
	public double speed;
	public double direction;
	public String owner;

	/* 車の識別番号フィールドは、車が生成されたときに決まっており、不変であるためfinalであるべき */
	static final int VehicleID = 123456789;
	public int VehicleCategoryID;
}
