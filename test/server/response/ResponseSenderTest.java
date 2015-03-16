package server.response;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResponseSenderTest {
	private Response response;
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
		Response response = new Response("test", "test".getBytes(), new HashMap<String,String>(), 200, ResponseCodes.getReason("200"));
		ResponseSender sender = new ResponseSender(response, out);
		
		sender.send();
		
		assertEquals(response.getResponseAsString(),out.toString());	
		
		
	}
	
	
}
