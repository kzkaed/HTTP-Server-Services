package server.request;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Hashtable;

import org.junit.Test;




import server.request.RequestParser;
import server.Constants;




public class RequestParserTest {
	String HEADERS_END = Constants.HEADERS_END;
	String CRLF = Constants.CRLF;
	String COLON = Constants.COLON;
		
	@Test
	public void testReadsRequestLine() throws IOException{
		String request = "GET /test/index HTTP1/1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(in);
		Request resultRequest = parser.parseRequest();
		
		Request expectRequest = new Request("GET","/test/index","HTTP1/1", null, "GET /test/index HTTP1/1",null,null);
		
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
	public void testQueryStringParsed() throws IOException{
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
	}
	
	@Test
	public void testURlEncodedParsed() throws IOException{
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
	}
	
	@Test
	public void testURlEncodedParsedParametersObscure() throws IOException{
		String request = "GET /test/index?name=kristin%20kaeding&this=that%20A%2BB%3DC HTTP1/1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		
		Request expectRequest = new Request("GET","/test/index","HTTP1/1", null, "GET /test/index?name=kristin%20kaeding&this=that%20A%2BB%3DC HTTP1/1",null,null);
		RequestParser parser = new RequestParser(in);
		Request resultRequest = parser.parseRequest();
		assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
		assertEquals(resultRequest.getProtocolVersion(),expectRequest.getProtocolVersion());
		assertEquals(resultRequest.getRequestLine(),expectRequest.getRequestLine());
		assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
		assertEquals(resultRequest.getURI(),expectRequest.getURI());
		assertEquals(resultRequest.getClass(),resultRequest.getClass());
		assertEquals(resultRequest.getParmeters().get("name").toString(), "kristin kaeding" );
		assertEquals(resultRequest.getParmeters().get("this").toString(), "that A+B=C" );
		
	}
	
	@Test
	public void testReadsTheRemainingRequestifAny() throws IOException {
		//String request = "GET / HTTP1/1";
		
		String restOfRequest = "Host: scutigera.com" + CRLF 
				+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:36.0) Gecko/20100101 Firefox/36.0"+ CRLF
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"+ CRLF
				+ "Accept-Language: en-US,en;q=0.5"+ CRLF
				+ "Accept-Encoding: gzip, deflate"+ CRLF
				+ "Connection: keep-alive"+ HEADERS_END
				+ "body";
		
		ByteArrayInputStream inStream = new ByteArrayInputStream(restOfRequest.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(in);
		
		String[] resultHeaders = parser.readRemainingRequest();
		String headers = "Host: scutigera.com" + CRLF 
				+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:36.0) Gecko/20100101 Firefox/36.0"+ CRLF
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"+ CRLF
				+ "Accept-Language: en-US,en;q=0.5"+ CRLF
				+ "Accept-Encoding: gzip, deflate"+ CRLF
				+ "Connection: keep-alive";
		String body = "body";
		
		assertEquals(resultHeaders[0], headers);
		assertEquals(resultHeaders[1], body);
		
	}
	
	@Test
	public void testParseHeaders(){
		String request = "GET / HTTP1/1";
		String headers = "Host: scutigera.com" + CRLF 
				+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:36.0) Gecko/20100101 Firefox/36.0"+ CRLF
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"+ CRLF
				+ "Accept-Language: en-US,en;q=0.5"+ CRLF
				+ "Accept-Encoding: gzip, deflate"+ CRLF
				+ "Connection: keep-alive";
		
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(in);
		Hashtable<String,String> headersPairs = parser.parseHeaders(headers);
		assertEquals(headersPairs.get("User-Agent"),"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:36.0) Gecko/20100101 Firefox/36.0");
		assertEquals(headersPairs.get("Host"),"scutigera.com");
		assertEquals(headersPairs.get("Accept"),"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		assertEquals(headersPairs.get("Connection"),"keep-alive");
		assertEquals(headersPairs.get("Accept-Encoding"),"gzip, deflate");
		
	}
}
