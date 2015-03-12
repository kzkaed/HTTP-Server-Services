package server.response;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResponseTest {
	
	Response response;
	@Before
	public void setUp() throws Exception {
		
		HashMap<String,String> headers = new HashMap<String,String>();
		headers.put("Server", "Kristin Server");
		String headersStr = "test";
		response = new Response("body","body".getBytes(),headers,headersStr);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getHeadersTest(){
		assertEquals(response.getHeaders(),"test");
	}
	
	@Test
	public void getBodyTest(){
		assertEquals(response.getBody(),"body");
	}
	
	@Test
	public void getBodyBytesTest(){
		assertEquals(response.getBodyBytes().toString(),"[B@6267c3bb");
	}
	

}
