package server.request;

import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.Test;

import server.request.Request;

public class RequestTest {
	

	@Test
	public void testRequestGetsRequestTokens(){
		String method = "";
		String uri = "";
		String protocolVersion = "";
		Hashtable<String,String> headers = new Hashtable<String,String>();
		String requestLine = "GET / HTTP1.1";	
		String requestBody = "";
		Hashtable<String,String> parameters = new Hashtable<String,String>();
		
		Request request = new Request(method,uri,protocolVersion, headers,requestLine,requestBody,parameters);
		assertEquals(request.getRequestLine(), requestLine);
		assertEquals(request.getMethod(), method);
		assertEquals(request.getProtocolVersion(), protocolVersion);
		assertEquals(request.getURI(), uri);
		assertNotNull(request.getParmeters());
		assertNotNull(request.getHeaders());
	}
	
	@Test
	public void testSetParameter(){
		Request request = new Request();
		request.setParameter("howya", "doin");
		assertEquals(request.getParmeters().get("howya"), "doin");
	}
	
	@Test
	public void testSetUri(){
		Request request = new Request();
		request.setURI("test");
		assertEquals(request.getURI(), "test");
	}
	
	@Test
	public void testSetMethod(){
		Request request = new Request();
		request.setMethod("GET");
		assertEquals(request.getMethod(), "GET");
	}
	

}


