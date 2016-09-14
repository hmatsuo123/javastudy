package ch24.ex03;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class PrintDateFormat {
	public static void main(String[] args) throws ParseException {
		Locale japan = new Locale("ja", "JP");
		Date date = DateFormat.getDateInstance(DateFormat.SHORT, japan).parse("2016/9/16");
		System.out.println(DateFormat.getDateInstance(DateFormat.FULL).format(date));
		System.out.println(DateFormat.getDateInstance(DateFormat.LONG).format(date));
		System.out.println(DateFormat.getDateInstance(DateFormat.MEDIUM).format(date));
		System.out.println(DateFormat.getDateInstance(DateFormat.SHORT).format(date));
	}
}
