package ch24.ex02;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class MoneySymbol {
	public static void main(String[] args) {
		List<Locale> list = new ArrayList<>();
		list.add(Locale.JAPAN);
		list.add(Locale.US);
        list.add(Locale.UK);
        list.add(Locale.ITALY);
        list.add(Locale.CHINA);
        list.add(Locale.CANADA);

        for (Locale locale : list) {
            Currency cur = Currency.getInstance(locale);
            System.out.println(locale.toString() + "\t" + cur.getSymbol());
        }
    }
}
