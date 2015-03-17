package server.response.assets;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.Request;
import server.response.Response;
import server.response.assets.DynamicAsset;
import views.HtmlView;

public class DynamicAssetTest {
	private Request request;
	private Asset dynamicAsset;
	
	@Before
	public void setUp() throws Exception {
		dynamicAsset = new DynamicAsset();
		request = new Request();
		request.setMethod("GET");
		request.setURI("showParams");
		request.setParameter("param1", "bachelard's poetics of space");
	}

	@Test
	public void testCanHandleRoute() {
		assertTrue(dynamicAsset.canHandle(request));
	}
	
	@Test
	public void testExecutesRoute() throws MalformedURLException, UnsupportedEncodingException {
		Response response = dynamicAsset.execute(request);
		assertEquals("<!doctype html><html><head></head><body>param1:bachelard's poetics of space<br></body></html>", response.getBody());
	}
	
	@Test
	public void testGeneratesHtml() throws MalformedURLException, UnsupportedEncodingException {
		String body = ((DynamicAsset) dynamicAsset).retrieveBody(request);
		
		assertEquals("<!doctype html><html><head></head><body>param1:bachelard's poetics of space<br></body></html>", body);
	}

}
