package server.response.assets;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import server.request.Request;
import server.response.Response;

public class GetTest {

	Asset get;
	@Before
	public void setUp() throws Exception {
		get = new Get();
	}

	@Test
	public void testReturnsTrueIfGetMethod() {
		Request request = new Request();
		request.setMethod("GET");
		assertTrue(get.canHandle(request));
	}

	@Test
	public void testExecuteReturns200() throws MalformedURLException, UnsupportedEncodingException {
		Request request = new Request();
		request.setMethod("GET");
		Response response = get.execute(request);
		assertNotNull(response);
		assertEquals("HTTP/1.1 200 OK\r\n", response.getStatusLine());
	}

}
