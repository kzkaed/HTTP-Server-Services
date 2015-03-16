package server.response.assets;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.Request;
import server.response.Response;

public class PutTest {
	private String CRLF = server.constants.Constant.CRLF;
	private Put put;
	private Request request;
	@Before
	public void setUp() throws Exception {
		put = new Put();
		request = new Request();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCanHandlePutRequest() {
		request.setMethod("PUT");
		request.setURI("/");
		assertTrue(put.canHandle(request));
	}
	
	@Test
	public void testExecuteSimplePutRequest() throws MalformedURLException, UnsupportedEncodingException {
		request.setMethod("PUT");
		request.setURI("/");
		Response response = put.execute(request);
		assertEquals("HTTP/1.1 200 OK" + CRLF, response.getStatusLine());
	}

}
