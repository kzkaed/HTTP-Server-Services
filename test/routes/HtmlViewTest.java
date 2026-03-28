package routes;

import static org.junit.Assert.*;

import org.junit.Test;

import views.HtmlView;

public class HtmlViewTest {

	@Test
	public void testHTMLbuilds() {
		assertEquals("<!doctype html><html><head></head><body></body></html>", new HtmlView().build());
	}

}
