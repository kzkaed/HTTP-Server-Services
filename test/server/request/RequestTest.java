package server.request;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Hashtable;

import org.junit.Test;

public class RequestTest {
	

	@Test
	public void test(){
		String method = "";
		String url = "";
		String protocolVersion = "";
		Hashtable<String,String> headers = null;
		String requestLine = "GET / HTTP1.1";	
		String requestBody = "";
		
		Request request = new Request(method,url,protocolVersion, headers,requestLine,requestBody);
		assertEquals(request.requestLine, requestLine);
	
		
	}
	
	
	

}


