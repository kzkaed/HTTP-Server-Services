package server.request;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParametersParserURLTest {

	private ParametersParser paramsParser;
	@Before
	public void setUp() throws Exception {
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws MalformedURLException, URISyntaxException {
		
		String host = InetAddress.getLoopbackAddress().getHostName().toString();
		
		URL url = new URL("http",host,5000, "/test/index");//"http://localhost:5000/test/index?name=kristin#1"
		String userInfo = null;
		String path = "/test/index"; 
		String query = "name=kristin";
		String fragment = "1";
		URI uri = new URI("http",userInfo,host,5000,path,query,fragment);
		
		paramsParser = new ParametersParserURL(url.toString());
		assertEquals(paramsParser.getFilename(),"/test/index");
		
		
	}

	

}
