package views;

import static org.junit.Assert.*;

import org.junit.Test;

public class HtmlViewTest {

	@Test
	public void testBuildsEmptyPage() {
		assertEquals(new HtmlView().build(), "<!doctype html><html><head></head><body></body></html>");
	}

}
