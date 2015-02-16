package server;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.request.HttpRequestParser;

public class HttpRequestParserTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

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
		String response = parser.parse();
		assertEquals(response, "HTTP/1.1 200 OK\r\n");

	}
	
	@Test
	public void testPOSTRequestResponse() {
		String request = "POST /bugreport.php HTTP/1.1";
		HttpRequestParser parser = new HttpRequestParser(request);
		String response = parser.parse();
		assertEquals(response, "HTTP/1.1 200 OK\r\n");
	}

}
