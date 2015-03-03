package server.request;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Hashtable;

import org.junit.Test;


public class ParametersParserURLTest {

	@Test
	public void testGetPairsAndDecodes() throws MalformedURLException, UnsupportedEncodingException{
		
		ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin&id=1&this=that+A%26B%3DC#paragraph2");
		Hashtable<String,String> pairs = paramsParser.getParameterPairs();
		assertEquals(pairs.get("name"),"kristin");	
		assertEquals(pairs.get("id"),"1");
		assertEquals(pairs.get("this"),"that A&B=C");
	}
	
	@Test 
	public void testQuery() throws MalformedURLException, UnsupportedEncodingException{
		ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin&id=1&this=that+A%26B%3DC#paragraph2");
		String query = paramsParser.getQuery();
		assertEquals(query,"name=kristin&id=1&this=that+A%26B%3DC");
	}

	@Test
	public void testGetFilename() throws MalformedURLException, UnsupportedEncodingException{
		ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin&id=1&this=that+A%26B%3DC#paragraph2");
		assertEquals(paramsParser.getFilename(),"/test?name=kristin&id=1&this=that+A%26B%3DC");
	}
	
	@Test
	public void testGetPath() throws MalformedURLException, UnsupportedEncodingException{
		ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin&id=1#paragraph2");
		assertEquals("/test",paramsParser.getPath());
	}

}
