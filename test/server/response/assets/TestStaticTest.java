package server.response.assets;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.Request;
import server.response.Response;

public class TestStaticTest {

	private TestStatic testStatic;
	private Request request;

	@Before
	public void setUp() throws Exception {
		testStatic = new TestStatic();
		request = new Request();
		request.setMethod("GET");
		request.setURI("/test/static");
		server.constants.Context.setPublicDirectory("public");
		
	}

	@Test
	public void testCanHandleRoute() {
		assertTrue(testStatic.canHandle(request));
	}
	
	@Test
	public void testExecutesRoute() throws MalformedURLException, UnsupportedEncodingException{
		Response response = testStatic.execute(request);
		
		assertEquals("<!doctype html><html><head></head><body>Test Static</body></html>",response.getBody());
		
	}

}
