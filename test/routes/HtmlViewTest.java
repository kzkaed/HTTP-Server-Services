package routes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Hashtable;
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
		assertEquals(htmlstring,"<!doctype html><html><head></head><body>Directory<br><a href=\"an item\">an item</a><br><a href=\"another item\">another item</a><br></body></html>");
	}
	
	@Test
	public void testHTMLbuildsParameters() {
		Hashtable<String,String> params= new Hashtable<String,String>();
		params.put("variable_1", "1");
		params.put("variable_2", "2");

		HtmlView html = new HtmlView(params);
		String htmlstring = html.build("parameters");
		assertEquals(htmlstring,"<!doctype html><html><head></head><body>variable_1 = 1variable_2 = 2</body></html>");
	}

}
