package server.response;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import routes.AssetManager;
import server.request.Request;
import server.response.assets.DirectoryAsset;
import server.response.assets.DynamicAsset;
import server.response.assets.FileNotFound;
import server.response.assets.GetFileStaticAsset;
import server.response.assets.ImageAsset;
import server.response.assets.Options;
import server.response.assets.Parameter;
import server.response.assets.Post;
import server.response.assets.Put;
import server.response.assets.TestStatic;

public class ResponseBuilderTest {
	
	private final String CRLF = "\r\n";
	private final String HEADERS_END = CRLF + CRLF;
	AssetManager manager;
	
	@Before
	public void setUp(){
		manager = new AssetManager();
		manager.register(new GetFileStaticAsset());
		manager.register(new DynamicAsset());
		manager.register(new DirectoryAsset());
		manager.register(new Parameter());
	
		manager.register(new ImageAsset());
		manager.register(new TestStatic());
		manager.register(new Options());
		manager.register(new Post());
		manager.register(new Put());
	}
	
	
	@Test
	public void testPOSTRequestResponse() throws IOException{
		String requestLine = "POST / HTTP/1.1";
		Request request = new Request("POST","/","HTTP/1.1",null,null,requestLine,null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		
		Response response = responseBuilder.buildResponse(manager);
		assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK" + CRLF);
	}

	@Test
	public void testPUTRequestResponse() throws IOException{
		String requestLine = "PUT / HTTP/1.1";
		Request request = new Request("POST","/","HTTP/1.1",null,null,requestLine,null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		Response response = responseBuilder.buildResponse(manager);
		assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK" + CRLF);
	}
	
	@Test
	public void testOPTIONRequestResponse() throws IOException{
		String requestLine = "OPTIONS / HTTP/1.1";
		Request request = new Request("OPTIONS","/","HTTP/1.1",null,null,requestLine,null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		Response response = responseBuilder.buildResponse(manager);
		assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT" + CRLF + CRLF, response.getResponseAsString());
	}

	@Test
	public void testGetTakesRoute() throws IOException{
		String requestLine = "GET /test/static HTTP/1.1";
		Request request = new Request("GET","/test/static","HTTP/1.1",null,null,requestLine,null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		Response response = responseBuilder.buildResponse(manager);
		String responseExpected = "HTTP/1.1 200 OK" + CRLF
		+"Server: Kristin Server" + CRLF
		+"Content-Type: text/html" + HEADERS_END
		+"<!doctype html><html><head></head><body>Test Static</body></html>";
		assertEquals(response.getResponseAsString(), responseExpected);
		
	}
	
	@Test
	public void test404IfFileNotFound() throws IOException{
		String requestLine = "GET /jam HTTP/1.1";
		Request request = new Request("GET","/jam","HTTP/1.1",null,null,requestLine,null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		Response responseReceived = responseBuilder.buildResponse(manager);
		String response = "HTTP/1.1 404 Not Found" + CRLF 
				+ "Server: Kristin Server" + CRLF 
				+ "Content-Type: text/html" + HEADERS_END
				+ "Not Found";
		
		assertEquals(response, responseReceived.getResponseAsString());	
	}
	
	/*@Test
	public void test502NotImplemented() throws IOException{
		Request request = new Request("","","",null,null,"",null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		Response responseReceived = responseBuilder.buildResponse(manager);
		String response = "HTTP/1.1 502 Not Implemented" + CRLF 
				+ "Server: Kristin Server" + CRLF 
				+ "Accept-Ranges: bytes" + CRLF 
				+ "Content-Type: text/html" + HEADERS_END
				+ "502 Not Implemented";
		
		assertEquals(response, responseReceived.getResponseAsString());	
	}*/
	

}
