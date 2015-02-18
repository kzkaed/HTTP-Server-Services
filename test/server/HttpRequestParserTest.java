package server;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.request.HttpRequestParser;

public class HttpRequestParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGETRequestResponse() {
		String request = "GET / HTTP/1.1";
		HttpRequestParser parser = new HttpRequestParser(request);
		parser.parse();
		String statusLine = parser.getStatusLine();
		
		assertEquals(statusLine, "HTTP/1.1 200 OK\r\n");

	}
	
	@Test
	public void testPOSTRequestResponse() {
		String request = "POST / HTTP/1.1";
		HttpRequestParser parser = new HttpRequestParser(request);
		String response = parser.parse();
		assertEquals(response, "HTTP/1.1 200 OK\r\n");
	}
	
	@Test
	public void testPUTRequestResponse() {
		String request = "PUT / HTTP/1.1";
		HttpRequestParser parser = new HttpRequestParser(request);
		String response = parser.parse();
		assertEquals(response, "HTTP/1.1 200 OK\r\n");
	}
	@Test
	public void testOPTIONRequestResponse() {
		String request = "OPTIONS /users/me http/1.1";
		HttpRequestParser parser = new HttpRequestParser(request);
		String response = parser.parse();
		assertEquals(response, "HTTP/1.1 200 OK\r\nAllow:GET,HEAD,POST,OPTIONS,PUT\r\n");
	}

}
