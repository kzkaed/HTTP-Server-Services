package server.request;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Hashtable;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RequestTest {

	@Before
	public void setUp() throws Exception {
		String method = "GET";
		String url = "/";
		String body = "";
		Hashtable<String,String> headers = null;
		headers.put("User-Agent", "");
		String statusLine = null;	
		Request request = new Request(method,url,body,headers,statusLine);
	}

	@After
	public void tearDown() throws Exception {
	}


}


