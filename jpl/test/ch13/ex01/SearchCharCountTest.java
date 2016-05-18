package ch13.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

public class SearchCharCountTest {

	@Test
	public void test() {
		SearchCharCount searchCharCount = new SearchCharCount();
		assertEquals(2, searchCharCount.charCount("qwertyuiopq", 'q'));
		assertEquals(2, searchCharCount.charCount("matsuohiroki", 'o'));
		assertEquals(0, searchCharCount.charCount("matsuohiroki", '9'));
	}

}
