package server.request;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.Utilities;

public class ParametersParserURLTest {

	
	
	
	@Test
	public void testGetPairs() throws MalformedURLException{
		
		ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin#1");
		Hashtable<String,String> pairs = paramsParser.getParameterNameValuePairs();
		assertEquals(pairs.get("name"),"kristin");
		
	}
	
	@Test 
	public void testQuery() throws MalformedURLException{
		
		ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin&id=1#paragraph2");
		String query = paramsParser.getQuery();
		assertEquals(query,"name=kristin&id=1");
		
	}
	
	@Test
	public void testGetPairsMany() throws MalformedURLException{
		
		ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin&id=1&hat=fedora#2");
		Hashtable<String,String> pairs = paramsParser.getParameterNameValuePairs();
		assertEquals(pairs.get("name"),"kristin");
		assertEquals(pairs.get("id"),"1");	
	}
	
	@Test
	public void testGetReference() throws MalformedURLException{
		
		ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin&id=1#paragraph2");
		assertEquals(paramsParser.getRef(),"paragraph2");
	}
	
	@Test
	public void testGetFilename() throws MalformedURLException{
		ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin&id=1#paragraph2");
		assertEquals(paramsParser.getFilename(),"/test?name=kristin&id=1");
	}
	
	@Test
	public void testGetPath() throws MalformedURLException{
		
		ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin&id=1#paragraph2");
		assertEquals(paramsParser.getPath(),"/test");
	}
	
	

	

}
