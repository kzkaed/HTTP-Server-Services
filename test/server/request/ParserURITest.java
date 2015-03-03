package server.request;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParserURITest {

	
	@Test
	public void testParsesQuery() throws URISyntaxException {
		String requestUri = "/test/index?name=kristin&id=1";
		ParametersParser parser = new ParserURI(requestUri);
		
		String expectedQuery = "name=kristin&id=1";
		assertEquals(expectedQuery, parser.getQuery()); 
	}
	
	@Test
	public void testParsesQueryDecoded() throws URISyntaxException {
		String requestUri = "/test/index?name=kristin%20kaeding&id=1";
		ParametersParser parser = new ParserURI(requestUri);
		
		String expectedQuery = "name=kristin kaeding&id=1";
		assertEquals(expectedQuery,parser.getQuery()); 
	}
	
	@Test
	public void testParsesParameters() throws URISyntaxException {
		String requestUri = "/test/index?name=kristin&id=1";
		ParametersParser parser = new ParserURI(requestUri);
		
		String expectedParameter = "name=kristin&id=1";
		assertEquals(expectedParameter,parser.getQuery()); 
	}
	
	@Test
	public void testParsesParametersDecoded() throws URISyntaxException, UnsupportedEncodingException{
		String requestUri = "/test/index?name=kristin%20kaeding&id=1&language=test";
		
		ParametersParser parser = new ParserURI(requestUri);
		
		String expectedParameter = "kristin kaeding";
		String expectedParameter2 = "1";
		String expectedParameter3 = "test";
		assertEquals(expectedParameter,parser.getParameterPairs().get("name")); 
		assertEquals(expectedParameter2,parser.getParameterPairs().get("id")); 
		assertEquals(expectedParameter3,parser.getParameterPairs().get("language")); 
	}

}
