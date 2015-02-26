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

	private ParametersParser paramsParser;
	
	@Test
	public void test() throws MalformedURLException, URISyntaxException {
		
		String host = InetAddress.getLoopbackAddress().getHostName().toString();
		
		URL url = new URL("http://localhost:5000/test/index");//"http://localhost:5000/test/index?name=kristin#1"
		String userInfo = null;
		String path = "/test/index"; 
		String query = "name=kristin";
		String fragment = "1";
		//URI uri = new URI("http",userInfo,host,5000,path,query,fragment);
		
		paramsParser = new ParametersParserURL(url.toString());
		assertEquals(paramsParser.getFilename(),"/test/index");
	}
	
	@Test
	public void testGetPairs() throws MalformedURLException{
		paramsParser = new ParametersParserURL("http://localhost:5000/test?name=kristin#1");
		Hashtable<String,String> pairs = paramsParser.getParameterNameValuePairs();
		assertEquals(pairs.get("name"),"kristin");
		
	}
	
	@Test 
	public void testQuery() throws MalformedURLException{
		paramsParser = new ParametersParserURL("http://localhost:5000/test?name=kristin&id=1#paragraph2");
		String query = paramsParser.getQuery();
		assertEquals(query,"name=kristin&id=1");
		
	}
	
	@Test
	public void testGetPairsMany() throws MalformedURLException{
		paramsParser = new ParametersParserURL("http://localhost:5000/test?name=kristin&id=1&hat=fedora#2");
		Hashtable<String,String> pairs = paramsParser.getParameterNameValuePairs();
		assertEquals(pairs.get("name"),"kristin");
		assertEquals(pairs.get("id"),"1");	
	}
	
	@Test
	public void testGetReference() throws MalformedURLException{
		paramsParser = new ParametersParserURL("http://localhost:5000/test?name=kristin&id=1#paragraph2");
		assertEquals(paramsParser.getRef(),"paragraph2");
	}
	
	@Test
	public void testGetFilename() throws MalformedURLException{
		paramsParser = new ParametersParserURL("http://localhost:5000/test?name=kristin&id=1#paragraph2");
		assertEquals(paramsParser.getFilename(),"/test?name=kristin&id=1");
	}
	
	@Test
	public void testGetPath() throws MalformedURLException{
		paramsParser = new ParametersParserURL("http://localhost:5000/test?name=kristin&id=1#paragraph2");
		assertEquals(paramsParser.getPath(),"/test");
	}
	
	

	

}
