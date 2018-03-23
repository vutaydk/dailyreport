import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import common.util.StringFormat;

public class StringFormatTest {
	@Test
	public void testFormatParam1() {
		String formated = StringFormat.format("a{0}b{1}c{2}", Arrays.asList("A","B","C"));
		String expected = "aAbBcC";
		assertEquals("", expected, formated);
	}
	@Test
	public void testFormatParam2() {
		String formated = StringFormat.format("a{0}b{1}c{0}", Arrays.asList("A","B","C"));
		String expected = "aAbBcA";
		assertEquals("", expected, formated);
	}
	@Test
	public void testFormatParam3() {
		String formated = StringFormat.format("a{0}b{1}c{4}", Arrays.asList("A","B","C"));
		String expected = "aAbBc{4}";
		assertEquals("", expected, formated);
	}
}
