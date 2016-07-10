package ch17.ex01;

import java.util.HashMap;
import java.util.Map;

public class Memory {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        double par = (double)runtime.freeMemory() / runtime.totalMemory();

        System.out.printf("%f%%%n", par * 100);

        Map<Integer, String> map = new HashMap<Integer, String>();
        for (int i = 0; i < 1000000; i++) {
        	map.put(i, "test");
        }

        par = (double)runtime.freeMemory() / runtime.totalMemory();
        System.out.printf("%f%%%n", par * 100);

        System.gc();
        par = (double)runtime.freeMemory() / runtime.totalMemory();
        System.out.printf("%f%%%n", par * 100);
    }
}
