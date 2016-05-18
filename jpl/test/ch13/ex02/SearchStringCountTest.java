package ch13.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

public class SearchStringCountTest {

	@Test
	public void test() {
		SearchStringCount searchStringCount = new SearchStringCount();
		assertEquals(1, searchStringCount.stringCount("matsuohiroki", "ma"));
		assertEquals(0, searchStringCount.stringCount("matsuohiroki", "masuo"));
		assertEquals(0, searchStringCount.stringCount(null, "masuo"));
	}

}
