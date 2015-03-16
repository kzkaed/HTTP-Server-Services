package server.response.assets;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.Request;
import server.response.Response;

public class TestDynamicTest {
	private Request request;
	private Asset dynamic;
	
	@Before
	public void setUp() throws Exception {
		dynamic = new TestDynamic();
		request = new Request();
		request.setMethod("GET");
		request.setURI("/test/dynamic");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCanHandleRoute() {
		assertTrue(dynamic.canHandle(request));
	}
	
	@Test
	public void testExecutesRoute() throws MalformedURLException, UnsupportedEncodingException {
		Response response = dynamic.execute(request);
		assertEquals("<!doctype html><html><head></head><body>test dynamic</body></html>", response.getBody());
	}

}
