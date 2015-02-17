package server.handler;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.handler.SingleClientHandler;
import server.mocks.MockSocket;



public class SingleClientHandlerTest {
	private SingleClientHandler handler;
	private MockSocket mockSocket;
	private OutputStream out;
	private OutputStream errorOut;


	@Before
	public void setUp() throws IOException {
		out = new ByteArrayOutputStream();
		errorOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		System.setErr(new PrintStream(errorOut));
	}
	
	@Test
	public void testRunProcessHTTPRequest() throws IOException{
		String request = "GET / HTTP/1.1";
		String response = "HTTP/1.1 200 OK\r\n";		
		mockSocket = new MockSocket("localhost",5000,request.getBytes());
		handler = new SingleClientHandler(mockSocket);
		handler.run();
		assertEquals(mockSocket.getOutputStream().toString(), response);
	}
	
	@Test
	public void testError() throws IOException{
		String request = " ";
		String response = "HTTP/1.1 200 OK\r\n";		
		mockSocket = new MockSocket("localhost",5000,request.getBytes());
		handler = new SingleClientHandler(mockSocket);
		handler.run();
		assertEquals("", errorOut.toString());	
	}
	
	@After
	public void tearDown() throws IOException {
		mockSocket.close();
		assertEquals(mockSocket.isClosed(),true);	
	}
}
