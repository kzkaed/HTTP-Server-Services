package server.request;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.RequestParser;

public class RequestParserTest {

	private final String CRLF = "\r\n";
	private final String SPACE = "\\s";
	private final String COLON = ": ";
	private final String HEADERS_END = CRLF + CRLF;
	
	@Test
	public void testGETParse() {
		String request = "GET /public/index.html HTTP1/1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		RequestParser parser = new RequestParser(in);
		String delimiters = "[ ]+";
		String[] tokens = parser.retreiveTokens(request,delimiters);
		assertEquals(tokens[1], "/public/index.html");
	}
	
	@Test
	public void recievesFullURI(){
		
	}
	
	@Test
	public void testReadsRequestLine(){
		String request = "GET /test/index HTTP1/1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		
		Request expectRequest = new Request("GET","/test/index","HTTP1/1", null, "GET /test/index HTTP1/1",null);
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
	public void testQueryStringParsed(){
		String request = "GET /test/index?id=1&test=true#REF HTTP1/1";
		ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		
		Request expectRequest = new Request("GET","/test/index?id=1&test=true#REF","HTTP1/1", null, "GET /test/index?id=1&test=true#REF HTTP1/1",null);
		RequestParser parser = new RequestParser(in);
		Request resultRequest = parser.parseRequest();
		assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
		assertEquals(resultRequest.getProtocolVersion(),expectRequest.getProtocolVersion());
		assertEquals(resultRequest.getRequestLine(),expectRequest.getRequestLine());
		assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
		assertEquals(resultRequest.getURI(),expectRequest.getURI());
		assertEquals(resultRequest.getClass(),resultRequest.getClass());
		
	}

	

	
	
	
	

}
