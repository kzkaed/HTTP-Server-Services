package server.response;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DynamicAssetTest {

	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGeneratesHtml() throws MalformedURLException {
		String uri = "/test/index";
		DynamicAsset asset = new DynamicAsset(uri);
		String html = asset.generatePage();
		assertEquals("<!doctype html><html><head></head><body>/test/index</body></html>",html);
	}

}
