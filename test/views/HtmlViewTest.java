package views;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HtmlViewTest {

	private HtmlView html;
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuildsBasicHtmlStructure() {
		html = new HtmlView();
		assertEquals(html.build(), "<!doctype html><html><head></head><body></body></html>");
	}
	
	@Test
	public void testBuildsTemlplateBasedOnDirectoryType(){
		List<String> results = new ArrayList<String>();
		results.add("an item");
		html = new HtmlView(results);
		assertEquals(html.build("directory"),"<!doctype html><html><head></head><body>Directory<br><a href=\"an item\">an item</a><br></body></html>");
	}
	
	@Test
	public void testBuildsTemlplateBasedOnParmetersType(){
		Hashtable<String,String> params = new Hashtable<String,String>();
		params.put("variable_1", "param1 value");
		html = new HtmlView(params);
		assertEquals(html.build("parameters"),"<!doctype html><html><head></head><body>variable_1 = param1 valuevariable_2 = null</body></html>");
	}

}
