package server.request;

import static org.junit.Assert.*;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
		assertEquals(response,
				"HTTP/1.1 200 OK\r\nAllow:GET,HEAD,POST,OPTIONS,PUT\r\n");
	}

	@Test
	public void testGETParse() {
		String request = "GET /Public/index.html HTTP1/1";
		HttpRequestParser parser = new HttpRequestParser(request);
		String[] tokens = parser.retreiveTokens(request);
		assertEquals(tokens[1], "/Public/index.html");
	}

	@Test
	public void testResponseHeadersAreBuilt() {
		String request = "GET /Public/index.html HTTP1/1";
		HttpRequestParser parser = new HttpRequestParser(request);
		String headers = "Server: Kristin Server\r\n"
				+ "Accept-Ranges: bytes\r\n" + "Content-Type: text/html\r\n";
		String returnedHeaders = parser.buildResponseHeaders();
		assertEquals(returnedHeaders, headers);
	}

	@Test
	public void testFindRelativePathRoot() {
		String request = "GET / HTTP/1.1";
		HttpRequestParser parser = new HttpRequestParser(request);
		String relativePathRoot = parser.findPath("");
		assertNotNull(relativePathRoot);
	}

	@Test
	public void testGetDocumentBody() {
		String request = "GET / HTTP/1.1";
		HttpRequestParser parser = new HttpRequestParser(request);
		String documentBody = parser.getDocumentBody();
		String body = "<!doctype html><html>"
				+ "<head></head><body>Mushroom in the Rain</body></html>";
		assertEquals(documentBody, body);
	}

	@Test
	public void testGetContent() {
		String request = "GET /test.html HTTP/1.1";
		HttpRequestParser parser = new HttpRequestParser(request);
		String content = "<!doctype html><html><head><title>Test "
				+ "at root</title></head><body>Test at root</body></html>";
		String contentReceived = parser.getContent(request);
		assertEquals(content, contentReceived);

	}

	@Test
	public void testGetContentOnPath() {
		String request = "GET /test/test.html HTTP/1.1";
		HttpRequestParser parser = new HttpRequestParser(request);
		String content = "<!doctype html><html><head><title>HTTP-Server-Service Test HTML</title>"
				+ "</head><body><a href=\"http://www.scutigera.com\">Two</a> Mushroom in the Rain<br>with Ants Underneath.</body></html>";
		String contentReceived = parser.getContent(request);
		assertEquals(content, contentReceived);

	}

}
