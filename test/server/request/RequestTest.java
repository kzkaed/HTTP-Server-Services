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
		String body = "";
		Hashtable<String,String> headers = null;
		String requestLine = "GET / HTTP1.1";	
		String protocolVersion = "";
		Request request = new Request(method,url,body,headers,requestLine, protocolVersion);
		assertEquals(request.requestLine, requestLine);
	}
	
	
	

}


