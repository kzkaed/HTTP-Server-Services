package server.response;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.Request;
import server.request.RequestParser;

public class ResponseBuilderTest {
	
	private final String CRLF = "\r\n";
	private final String SPACE = "\\s";
	private final String COLON = ": ";
	private final String HEADERS_END = CRLF + CRLF;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGETRequestResponse() throws IOException {
		String requestLine = "GET / HTTP/1.1";
		Request request = new Request("GET","/","HTTP/1.1",null,null,requestLine);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		responseBuilder.buildResponse();
		String statusLine = responseBuilder.getStatusLine();
		assertEquals(statusLine, "HTTP/1.1 200 OK" + CRLF);
	}
	
	@Test
	public void testPOSTRequestResponse() throws IOException{
		String requestLine = "POST / HTTP/1.1";
		Request request = new Request("POST","/","HTTP/1.1",null,null,requestLine);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		String response = responseBuilder.buildResponse();
		assertEquals(response, "HTTP/1.1 201 Created" + CRLF);
	}

	@Test
	public void testPUTRequestResponse() throws IOException{
		
		String requestLine = "PUT / HTTP/1.1";
		Request request = new Request("POST","/","HTTP/1.1",null,null,requestLine);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		String response = responseBuilder.buildResponse();
		assertEquals(response, "HTTP/1.1 201 Created" + CRLF);
	}
	
	@Test
	public void testOPTIONRequestResponse() throws IOException{
		String requestLine = "OPTIONS / HTTP/1.1";
		Request request = new Request("OPTIONS","/","HTTP/1.1",null,null,requestLine);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		String response = responseBuilder.buildResponse();
		assertEquals(response,
				"HTTP/1.1 200 OK\r\nAllow:GET,HEAD,POST,OPTIONS,PUT" + CRLF);
	}
	
	@Test
	public void testResponseHeadersAreBuilt() {
		String requestLine = "GET / HTTP/1.1";
		Request request = new Request("GET","/","HTTP/1.1",null,null,requestLine);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		String headers = "Server: Kristin Server" + CRLF
				+ "Accept-Ranges: bytes" + CRLF + "Content-Type: text/html" + CRLF;
		String returnedHeaders = responseBuilder.buildResponseHeaders();
		assertEquals(returnedHeaders, headers);
	}

	@Test
	public void testFindRelativePathRoot() {
		String requestLine = "GET / HTTP/1.1";
		Request request = new Request("GET","/","HTTP/1.1",null,null,requestLine);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);

		String relativePathRoot = responseBuilder.findPath("");
		assertNotNull(relativePathRoot);
	}
	
	@Test
	public void testGetContent() {
		String requestLine = "GET /test.html HTTP/1.1";
		Request request = new Request("GET","/test.html","HTTP/1.1",null,null,requestLine);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		String content = "<!doctype html><html><head><title>Test "
				+ "at root</title></head><body>Test at root</body></html>";
		String contentReceived = responseBuilder.getResponseBody(request.getURI());
		assertEquals(content, contentReceived);

	}
	
	@Test
	public void testGetContentOnPath() {
		String requestLine = "GET /test/test.html HTTP/1.1";
		Request request = new Request("GET","/test/test.html","HTTP/1.1",null,null,requestLine);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		String content = "<!doctype html><html><head><title>HTTP-Server-Service Test HTML</title>"
				+ "</head><body><a href=\"http://www.scutigera.com\">Two</a> Mushroom in the Rain<br>with Ants Underneath.</body></html>";
		String contentReceived = responseBuilder.getResponseBody(request.getURI());
		assertEquals(content, contentReceived);

	}
	
	@Test
	public void testGetTakesRoute(){
		String requestLine = "GET /test/index HTTP/1.1";
		Request request = new Request("GET","/test/index","HTTP/1.1",null,null,requestLine);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		String content = "<!doctype html><html><head><title>HTTP-Server-Service Test HTML</title></head><body>Test</body></html>";
		String contentReceived = responseBuilder.getResponseBody(request.getURI());
		assertEquals(content, contentReceived);
		
	}
	
	@Test
	public void test404IfFileNotFound() throws IOException{
		String requestLine = "GET /jam HTTP/1.1";
		Request request = new Request("GET","/jam","HTTP/1.1",null,null,requestLine);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		
		String responseReceived = responseBuilder.buildResponse();
		String response = "HTTP/1.1 404 Not Found" + CRLF 
				+ "Server: Kristin Server" + CRLF 
				+ "Accept-Ranges: bytes" + CRLF 
				+ "Content-Type: text/html" + HEADERS_END
				+ "404 Not Found";
		
		assertEquals(response, responseReceived);	
	}
	
	
	
}
