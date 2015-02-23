package server.response;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResponseSenderTest {
	private String response;
	private OutputStream out;
	
	@Before
	public void setUp() throws Exception {
		out = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IOException {
		ResponseSender sender = new ResponseSender("test", out);
		String response = "test";
		sender.send();
		
		assertEquals(response,out.toString() );	
		
	}

}
