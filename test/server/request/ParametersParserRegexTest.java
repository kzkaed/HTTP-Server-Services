package server.request;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParametersParserRegexTest {
	@Test
	public void testGetPairsAndDecodes() throws MalformedURLException, UnsupportedEncodingException{
		
		ParametersParser paramsParser = new ParametersParserRegex("/test?name=kristin&id=1&this=that+A%26B%3DC");
		Hashtable<String,String> pairs = paramsParser.getParameterPairs();
		assertEquals(pairs.get("name"),"kristin");	
		assertEquals(pairs.get("id"),"1");
		assertEquals(pairs.get("this"),"that A&B=C");
	}
	
	@Test 
	public void testQuery() throws MalformedURLException, UnsupportedEncodingException{
		ParametersParser paramsParser = new ParametersParserRegex("/test?name=kristin&id=1&this=that+A%26B%3DC");
		String query = paramsParser.getQuery();
		assertEquals(query,"name=kristin&id=1&this=that+A%26B%3DC");
	}

	@Test
	public void testGetFilename() throws MalformedURLException, UnsupportedEncodingException{
		ParametersParser paramsParser = new ParametersParserRegex("/test?name=kristin&id=1&this=that+A%26B%3DC");
		assertEquals(paramsParser.getFilename(),"/test?name=kristin&id=1&this=that+A%26B%3DC");
	}
	
	@Test
	public void testGetPath() throws MalformedURLException, UnsupportedEncodingException{
		ParametersParser paramsParser = new ParametersParserRegex("/test?name=kristin&id=1");
		assertEquals("/test",paramsParser.getPath());
	}

}
