package ch24.ex01;

import java.util.Locale;
import java.util.ResourceBundle;

public class GlobalHello {
	public static void main(String[] args) {
		//Locale.setDefault(Locale.JAPANESE);
		Locale.setDefault(Locale.ENGLISH);
		ResourceBundle res = ResourceBundle.getBundle("ch24.ex01.GlobalRes");
		String msg;
		if (args.length > 0)
			msg = res.getString(GlobalRes.HELLO);
		else
			msg = res.getString(GlobalRes.GOODBYE);
		System.out.println(msg);
	}
}
