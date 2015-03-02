package routes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HtmlViewTest {

	@Test
	public void testHTMLbuilds() {
		HtmlView html = new HtmlView();
		String htmlstring = html.build();
		assertEquals(htmlstring,"<!doctype html><html><head></head><body>null</body></html>");
	}

}
