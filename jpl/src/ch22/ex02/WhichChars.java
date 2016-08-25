package ch22.ex02;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class WhichChars {
	private Set<Character> used = new HashSet<Character>();

    public WhichChars(String str) {
        for (int i = 0; i < str.length(); i++) {
            used.add(str.charAt(i));
        }
    }

    @Override
    public String toString() {
        Set<Character> used = new TreeSet<Character>(this.used);
        String desc = "[";
        for (Character i : used) {
            desc += (char) i;
        }
        return desc + "]";
    }

    public static void main(String[] args) {
        System.out.println(new WhichChars("Testing 1 2 3").toString());
    }
}
