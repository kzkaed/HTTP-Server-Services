package server.response;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;
import server.request.Request;

public class ResponseBuilderTest {
	
	private final String CRLF = "\r\n";
	private final String HEADERS_END = CRLF + CRLF;

	@Test
	public void testGETRequestResponse() throws IOException {
		String requestLine = "GET / HTTP/1.1";
		Request request = new Request("GET","/","HTTP/1.1",null,null,requestLine,null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		String statusLine = responseBuilder.getStatusLine();
		assertEquals(statusLine, "HTTP/1.1 200 OK" + CRLF);
	}
	
	@Test
	public void testPOSTRequestResponse() throws IOException{
		String requestLine = "POST / HTTP/1.1";
		Request request = new Request("POST","/","HTTP/1.1",null,null,requestLine,null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		String response = responseBuilder.buildResponse();
		assertEquals(response, "HTTP/1.1 201 Created" + CRLF);
	}

	@Test
	public void testPUTRequestResponse() throws IOException{
		String requestLine = "PUT / HTTP/1.1";
		Request request = new Request("POST","/","HTTP/1.1",null,null,requestLine,null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		String response = responseBuilder.buildResponse();
		assertEquals(response, "HTTP/1.1 201 Created" + CRLF);
	}
	
	@Test
	public void testOPTIONRequestResponse() throws IOException{
		String requestLine = "OPTIONS / HTTP/1.1";
		Request request = new Request("OPTIONS","/","HTTP/1.1",null,null,requestLine,null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		String response = responseBuilder.buildResponse();
		assertEquals(response,
				"HTTP/1.1 200 OK\r\nAllow:GET,HEAD,POST,OPTIONS,PUT" + CRLF);
	}
	
	@Test
	public void testResponseHeadersAreBuilt() {
		String requestLine = "GET / HTTP/1.1";
		Request request = new Request("GET","/","HTTP/1.1",null,null,requestLine,null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		String headers = "Server: Kristin Server" + CRLF
							+ "Accept-Ranges: bytes" + CRLF 
							+ "Content-Type: text/html" + CRLF;
		String returnedHeaders = responseBuilder.buildResponseHeaders();
		assertEquals(returnedHeaders, headers);
	}


	
	@Test
	public void testGetTakesRoute() throws IOException{
		String requestLine = "GET /test/index HTTP/1.1";
		Request request = new Request("GET","/test/index","HTTP/1.1",null,null,requestLine,null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		String response = responseBuilder.buildResponse();
		String responseExpected = "HTTP/1.1 200 OK" + CRLF
		+"Server: Kristin Server" + CRLF
		+"Accept-Ranges: bytes" + CRLF
		+"Content-Type: text/html" + HEADERS_END
		+"<!doctype html><html><head></head><body>Test</body></html>";
		assertEquals(response, responseExpected);
		
	}
	
	@Test
	public void test404IfFileNotFound() throws IOException{
		String requestLine = "GET /jam HTTP/1.1";
		Request request = new Request("GET","/jam","HTTP/1.1",null,null,requestLine,null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		
		String responseReceived = responseBuilder.buildResponse();
		String response = "HTTP/1.1 404 Not Found" + CRLF 
				+ "Server: Kristin Server" + CRLF 
				+ "Accept-Ranges: bytes" + CRLF 
				+ "Content-Type: text/html" + HEADERS_END
				+ "404 Not Found";
		
		assertEquals(response, responseReceived);	
	}
	
	@Test
	public void test502NotImplemented() throws IOException{
		
		Request request = new Request("","","",null,null,"",null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		
		String responseReceived = responseBuilder.buildResponse();
		String response = "HTTP/1.1 502 Not Implemented" + CRLF 
				+ "Server: Kristin Server" + CRLF 
				+ "Accept-Ranges: bytes" + CRLF 
				+ "Content-Type: text/html" + HEADERS_END
				+ "502 Not Implemented";
		
		assertEquals(response, responseReceived);	
	}
	
	@Test
	public void testIfMethodIsNOTImplemented() throws IOException{
		Request request = new Request("XYZ","","",null,null,"", null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		
		assertFalse(responseBuilder.isMethodImplemented());		
	}
	
	@Test
	public void testIfMethodIsImplemented() throws IOException{
		Request request = new Request("GET","","",null,null,"",null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		
		assertTrue(responseBuilder.isMethodImplemented());	
	}
	

	
	
	
	
	
	
}
