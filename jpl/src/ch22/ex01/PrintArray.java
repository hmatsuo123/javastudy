package ch22.ex01;

public class PrintArray {
    public void print(double[] array, int column) {
        for (double d : array) {
            System.out.printf("%." + column + "f%n", d);
        }
    }

    public static void main(String[] args) {
        double[] array = {1.0, 1.123456, 0.000000009};
        new PrintArray().print(array, 80);
    }
}
