package views;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContentViewTest {

	@Test
	public void testRendersContent() {
		assertEquals("<!doctype html><html><head></head><body>hello</body></html>", new ContentView("hello").build());
	}

	@Test
	public void testRendersEmptyContent() {
		assertEquals("<!doctype html><html><head></head><body></body></html>", new ContentView("").build());
	}

}
