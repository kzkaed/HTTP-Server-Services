package server.response.assets;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.Request;

public class OptionsTest {
	private final String CRLF = server.constants.Constant.CRLF;
	private final String HEADERS_END = server.constants.Constant.HEADERS_END;
	private Options options;
	@Before
	public void setUp() throws Exception {
		options = new Options();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCanHandleRequest() {
		String requestLine = "OPTIONS / HTTP/1.1";
		Request request = new Request("OPTIONS","/","HTTP/1.1",null,null,requestLine,null);
		assertTrue(options.canHandle(request));
	}
	
	@Test
	public void testExcutes() throws MalformedURLException, UnsupportedEncodingException {
		
		String requestLine = "OPTIONS / HTTP/1.1";
		Request request = new Request("OPTIONS","/","HTTP/1.1",null,null,requestLine,null);
		assertEquals("HTTP/1.1 200 OK" + CRLF 
				+ "Allow: GET,HEAD,POST,OPTIONS,PUT" + HEADERS_END,
				options.execute(request).getResponseAsString());
		
	}

}
