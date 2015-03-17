package server.response.assets;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.Request;
import server.response.Response;

public class DynamicPathExtTest {
	private Request request;
	private Asset dynamic;
	
	@Before
	public void setUp() throws Exception {
		dynamic = new DynamicPathExt();
		request = new Request();
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testItCanNOThandle(){
		request.setMethod("GET");
		request.setURI("/other");
		assertFalse(dynamic.canHandle(request));
	}

	@Test
	public void testCanHandleRoute() {
		request.setMethod("GET");
		request.setURI("/test/dynamic");
		assertTrue(dynamic.canHandle(request));
	}
	
	@Test
	public void testExecutesRoute() throws MalformedURLException, UnsupportedEncodingException {
		request.setMethod("GET");
		request.setURI("/test/dynamic");
		Response response = dynamic.execute(request);
		assertEquals("<!doctype html><html><head></head><body>/test/dynamic</body></html>", response.getBody());
	}

}
