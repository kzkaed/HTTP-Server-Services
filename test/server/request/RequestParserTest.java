package server.request;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.junit.Test;

import server.request.RequestParser;

public class RequestParserTest {

		
	@Test
	public void recievesFullURI(){
		
	}
	
	@Test
	public void testReadsRequestLine() throws UnsupportedEncodingException{
		String request = "GET /test%2Findex HTTP1/1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(in);
		Request resultRequest = parser.parseRequest();
		
		Request expectRequest = new Request("GET","/test/index","HTTP1/1", null, "GET /test%2Findex HTTP1/1",null,null);
		
		assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
		assertEquals(resultRequest.getProtocolVersion(),expectRequest.getProtocolVersion());
		assertEquals(resultRequest.getRequestLine(),expectRequest.getRequestLine());
		assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
		assertEquals(resultRequest.getURI(),expectRequest.getURI());
		assertEquals(resultRequest.getClass(),resultRequest.getClass());
	}
	
	@Test 
	public void decodeTest() throws UnsupportedEncodingException {
		String url = "https%3A%2F%2Fmywebsite%2Fdocs%2Fenglish%2Fsite%2Fmybook.do" +
	               "%3Frequest_type%3D%26type%3Dprivate";
		String decoded = java.net.URLDecoder.decode(url, server.Constants.UTF_8);
		assertEquals("https://mywebsite/docs/english/site/mybook.do?request_type=&type=private", decoded);
	}
	
	@Test
	public void testQueryStringParsed() throws UnsupportedEncodingException{
		String request = "GET /test/index?id=1&test=true#REF HTTP1/1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		
		Request expectRequest = new Request("GET","/test/index","HTTP1/1", null, "GET /test/index?id=1&test=true#REF HTTP1/1",null,null);
		RequestParser parser = new RequestParser(in);
		Request resultRequest = parser.parseRequest();
		assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
		assertEquals(resultRequest.getProtocolVersion(),expectRequest.getProtocolVersion());
		assertEquals(resultRequest.getRequestLine(),expectRequest.getRequestLine());
		assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
		assertEquals(resultRequest.getURI(),expectRequest.getURI());
		assertEquals(resultRequest.getClass(),resultRequest.getClass());
		
		//GET is asking for a Service Object called /test/index with Parmeters id = 1 & test = true.
	}
	
	@Test
	public void testURlEncodedParsed() throws UnsupportedEncodingException{
		String request = "GET /test/index?name=kristin%20kaeding HTTP1/1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		
		Request expectRequest = new Request("GET","/test/index","HTTP1/1", null, "GET /test/index?name=kristin%20kaeding HTTP1/1",null,null);
		RequestParser parser = new RequestParser(in);
		Request resultRequest = parser.parseRequest();
		assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
		assertEquals(resultRequest.getProtocolVersion(),expectRequest.getProtocolVersion());
		assertEquals(resultRequest.getRequestLine(),expectRequest.getRequestLine());
		assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
		assertEquals(resultRequest.getURI(),expectRequest.getURI());
		assertEquals(resultRequest.getClass(),resultRequest.getClass());
		assertEquals(resultRequest.getParmeters().get("name").toString(), "kristin kaeding" );
		
		//GET is asking for a Service Object called /test/index with Parmeters id = 1 & test = true.
	}

}
