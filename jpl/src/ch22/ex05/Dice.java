package ch22.ex05;

import java.util.Random;

public class Dice {
private final int side = 6;

    public int getRandomCount() {
        return new Random().nextInt(side) + 1;
    }

	public static void main(String[] args) {
		int tryCount = 10000;
        int count = 0;
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();

        for (int i = 0; i < tryCount; i++) {
            int sum = dice1.getRandomCount() + dice2.getRandomCount();
            if (sum == 7)
                count++;
        }
        int percent = (int) ((double) count / tryCount * 100);

        System.out.println(percent + "%[" + count + "/" + tryCount + "]");
    }
}
