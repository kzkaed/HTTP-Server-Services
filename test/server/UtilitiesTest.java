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
	
}
