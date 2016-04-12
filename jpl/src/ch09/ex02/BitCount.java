package ch09.ex02;

public class BitCount {
	//インターネットで検索してヒットしたものをそのまま使用
	//http://www.mwsoft.jp/programming/java/java_lang_integer_bit_count.html
	public static int myBitCount(int i) {
		  i = i - ((i >>> 1) & 0x55555555);
		  i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
		  i = (i + (i >>> 4)) & 0x0f0f0f0f;
		  i = i + (i >>> 8);
		  i = i + (i >>> 16);
		  return i & 0x3f;
		}

	public static void main(String[] args) {
		int int1 = 1;
		int int4 = 4;
		int int10 = 10;
		System.out.println("Integer.bitCount(" + int1 + "): \t" + Integer.bitCount(int1));
        System.out.println("BitCount.myBitCount(" + int1 + "): \t" + myBitCount(int1));
        System.out.println("Integer.bitCount(" + int4 + "): \t" + Integer.bitCount(int4));
        System.out.println("BitCount.myBitCount(" + int4 + "): \t" + myBitCount(int4));
        System.out.println("Integer.bitCount(" + int10 + "): \t" + Integer.bitCount(int10));
        System.out.println("BitCount.myBitCount(" + int10 + "): \t" + myBitCount(int10));
	}
}
