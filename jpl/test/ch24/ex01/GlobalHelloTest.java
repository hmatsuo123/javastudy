package ch24.ex01;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class GlobalHelloTest {

	@Test
	public void LocaleEnglishTest() {
		Locale.setDefault(Locale.ENGLISH);
		ResourceBundle res = ResourceBundle.getBundle("ch24.ex01.GlobalRes");
		String msg = res.getString(GlobalRes.HELLO);
		GlobalRes_en globalRes_en = new GlobalRes_en();
		Class<?> cl = globalRes_en.getClass();
		try {
			Field field = cl.getDeclaredField("Hello");
			field.setAccessible(true);
			try {
				assertEquals((field.get(globalRes_en).toString()), msg);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				fail(e.getMessage());
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void LocaleJapaneseTest() {
		Locale.setDefault(Locale.JAPANESE);
		ResourceBundle res = ResourceBundle.getBundle("ch24.ex01.GlobalRes");
		String msg = res.getString(GlobalRes.HELLO);
		GlobalRes_ja globalRes_ja = new GlobalRes_ja();
		Class<?> cl = globalRes_ja.getClass();
		try {
			Field field = cl.getDeclaredField("Hello");
			field.setAccessible(true);
			try {
				assertEquals((field.get(globalRes_ja).toString()), msg);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				fail(e.getMessage());
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
