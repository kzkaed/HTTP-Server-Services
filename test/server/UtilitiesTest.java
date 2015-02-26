package server;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.RequestParser;

public class UtilitiesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGETParse() {
		Utilities util = new Utilities();
		String request = "GET /public/index.html HTTP1/1";
		String delimiters = "[ ]+";
		String[] tokens = util.retreiveTokens(request,delimiters);
		assertEquals(tokens[1], "/public/index.html");
	}
	
	@Test
	public void testParseQueryWithManyParameters(){
		Utilities util = new Utilities();
		String[] tokens = util.retreiveTokens("name=kristin&id=1", "&");
		for(int i = 0; i < tokens.length; i++){
			System.out.println(tokens[i]);
			String[] tokens2 = util.retreiveTokens(tokens[i], "=");
			for(int j = 0; j < tokens2.length; j++){
				System.out.println(tokens2[j]);
			}
		}
	}
	
}
