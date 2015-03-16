package server.response;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.constants.Constants;

public class ResponseTest {
	
	Response response;
	String HEADERS_END = Constants.HEADERS_END;
	String CRLF = Constants.CRLF;
	String COLON = Constants.COLON;
	@Before
	public void setUp() throws Exception {
		
		HashMap<String,String> headers = new HashMap<String,String>();
		headers.put("Server", "Kristin Server");
		
		response = new Response("body","body".getBytes(),headers,200,ResponseCodes.getReason("200"));
	}

	@Test
	public void getHeadersTest(){
		assertEquals("Server: Kristin Server" + HEADERS_END, response.getHeaders());
	}
	
	@Test
	public void getBodyTest(){
		assertEquals(response.getBody(),"body");
	}
	
	@Test
	public void getBodyBytesTest(){	
		assertNotNull(response.getBodyBytes().getClass());
	}
	
	@Test
	public void testBuildResponseStatusLine(){
		response.setResponseStatusCode(200);
		response.setResponseStatusMessage("OK");
		
		assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK" + CRLF);

	}
	
	@Test
	public void testBuildResponseHeaders(){
		response.setResponseStatusCode(200);
		response.setResponseStatusMessage("OK");
		
		assertEquals(response.getHeaders(),"Server: Kristin Server" + HEADERS_END);

	}
	
	

	

}
