package ch01.ex15;

import static org.junit.Assert.*;

import org.junit.Test;

import ch01.ex15.LookupSample.SimpleLookup;

public class ex15Test {

	@Test
	public void test() {
		LookupSample lookupSample = new LookupSample();
		SimpleLookup simpleLookup = lookupSample.getSimpleLookup();

		String targetName = "matsuo";
		Object targetValue = "value";

		simpleLookup.add(targetName, targetValue);
		if (simpleLookup.find(targetName) == null) {
			fail("検索、もしくは登録に失敗");
		}

		simpleLookup.remove(targetName);
		if (simpleLookup.find(targetName) != null) {
			fail("削除に失敗");
		}

	}

}
