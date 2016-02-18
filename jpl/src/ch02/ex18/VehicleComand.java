package ch02.ex18;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ch02.ex01.Vehicle;

public class VehicleComand extends Vehicle {
	public static void main(String[] args) {
		try {
		      BufferedReader stdReader =
		        new BufferedReader(new InputStreamReader(System.in));
		      System.out.print("車の所有者を入力してください : ");
		      String owner;
		      VehicleComand vehicle = new VehicleComand();
		      while ((owner = stdReader.readLine()) != null) { // ユーザの一行入力を待つ
		        if (owner.equals("")) continue;
		        vehicle.owner = owner;
		        break;
		      }
		      System.out.println("この車の所有者は" + vehicle.owner + "さんです");
		    } catch (Exception e) {
		      e.getStackTrace();
		      System.exit(-1); // プログラムを終了
		    }
	}
}
