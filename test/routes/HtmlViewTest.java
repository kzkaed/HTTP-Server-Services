package routes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import views.HtmlView;

public class HtmlViewTest {

	@Test
	public void testHTMLbuilds() {
		HtmlView html = new HtmlView();
		String htmlstring = html.build();
		assertEquals(htmlstring,"<!doctype html><html><head></head><body></body></html>");
	}
	
	@Test
	public void testHTMLbuildsDirectory() {
		List<String> list = new ArrayList<String>();
		list.add("an item");
		list.add("another item");

		HtmlView html = new HtmlView(list);
		String htmlstring = html.build("directory");
		assertEquals(htmlstring,"<!doctype html><html><head></head><body>an item<br>another item<br></body></html>");
	}

}
