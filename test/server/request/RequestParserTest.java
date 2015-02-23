package server.request;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.RequestParser;

public class RequestParserTest {

	private final String CRLF = "\r\n";
	private final String SPACE = "\\s";
	private final String COLON = ": ";
	private final String HEADERS_END = CRLF + CRLF;
	

	@Test
	public void testGETRequestResponse() {
		String request = "GET / HTTP/1.1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(request,in);
		parser.buildResponse();
		String statusLine = parser.getStatusLine();
		assertEquals(statusLine, "HTTP/1.1 200 OK" + CRLF);
	}

	@Test
	public void testPOSTRequestResponse() {
		String request = "POST / HTTP/1.1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(request,in);
		String response = parser.buildResponse();
		assertEquals(response, "HTTP/1.1 201 Created" + CRLF);
	}

	@Test
	public void testPUTRequestResponse() {
		
		String request = "PUT / HTTP/1.1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(request, in);
		String response = parser.buildResponse();
		assertEquals(response, "HTTP/1.1 201 Created" + CRLF);
	}

	@Test
	public void testOPTIONRequestResponse() {
		String request = "OPTIONS /users/me http/1.1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(request, in);
		String response = parser.buildResponse();
		assertEquals(response,
				"HTTP/1.1 200 OK\r\nAllow:GET,HEAD,POST,OPTIONS,PUT" + CRLF);
	}

	@Test
	public void testGETParse() {
		String request = "GET /public/index.html HTTP1/1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(request, in);
	
		String[] tokens = parser.retreiveTokens(request,null);
		assertEquals(tokens[1], "/public/index.html");
	}

	@Test
	public void testResponseHeadersAreBuilt() {
		String request = "GET /public/index.html HTTP1/1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(request, in);
		String headers = "Server: Kristin Server" + CRLF
				+ "Accept-Ranges: bytes" + CRLF + "Content-Type: text/html" + CRLF;
		String returnedHeaders = parser.buildResponseHeaders();
		assertEquals(returnedHeaders, headers);
	}

	@Test
	public void testFindRelativePathRoot() {
		String request = "GET / HTTP/1.1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(request, in);
		String relativePathRoot = parser.findPath("");
		assertNotNull(relativePathRoot);
	}

	@Test
	public void testGetContent() {
		String request = "GET /test.html HTTP/1.1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(request, in);
		String content = "<!doctype html><html><head><title>Test "
				+ "at root</title></head><body>Test at root</body></html>";
		String contentReceived = parser.getBody(request);
		assertEquals(content, contentReceived);

	}

	@Test
	public void testGetContentOnPath() {
		String request = "GET /test/test.html HTTP/1.1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(request, in);
		String content = "<!doctype html><html><head><title>HTTP-Server-Service Test HTML</title>"
				+ "</head><body><a href=\"http://www.scutigera.com\">Two</a> Mushroom in the Rain<br>with Ants Underneath.</body></html>";
		String contentReceived = parser.getBody(request);
		assertEquals(content, contentReceived);

	}
	
	@Test
	public void testGetTakesRoute(){
		String request = "GET /test/index HTTP/1.1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(request, in);
		String content = "<!doctype html><html><head><title>HTTP-Server-Service Test HTML</title></head><body>Test</body></html>";
		String contentReceived = parser.getBody(request);
		assertEquals(content, contentReceived);
		
	}
	
	@Test
	public void test404IfFileNotFound(){
		String request = "GET /jam HTTP/1.1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(request, in);
		String responseReceived = parser.buildResponse();
		String response = "HTTP/1.1 404 Not Found" + CRLF 
				+ "Server: Kristin Server" + CRLF 
				+ "Accept-Ranges: bytes" + CRLF 
				+ "Content-Type: text/html" + HEADERS_END
				+ "404 Not Found";
		
		assertEquals(response, responseReceived);	
	}
	
	

}
