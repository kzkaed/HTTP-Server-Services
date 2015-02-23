package server.request;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Hashtable;

import org.junit.Test;

public class RequestTest {
	

	@Test
	public void testRequestGetsRequestTokens(){
		String method = "";
		String uri = "";
		String protocolVersion = "";
		Hashtable<String,String> headers = null;
		String requestLine = "GET / HTTP1.1";	
		String requestBody = "";
		
		Request request = new Request(method,uri,protocolVersion, headers,requestLine,requestBody);
		assertEquals(request.getRequestLine(), requestLine);
		assertEquals(request.getMethod(), method);
		assertEquals(request.getProtocolVersion(), protocolVersion);
		assertEquals(request.getURI(), uri);
	
		
	}
	
	
	
	
	

}


