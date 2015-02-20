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
import log.Logger;
import log.mocks.StringLogger;

public class SingleClientHandlerTest {
	private SingleClientHandler handler;
	private MockSocket mockSocket;
	private OutputStream out;
	private OutputStream errorOut;
	private Logger logger;

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
		String statusLine = "HTTP/1.1 200 OK\r\n";		
		mockSocket = new MockSocket("localhost",5000,request.getBytes());
		handler = new SingleClientHandler(mockSocket,new StringLogger());
		handler.run();
		assertEquals(mockSocket.getOutputMock(), statusLine);	
	}
	

	
	
	@After
	public void tearDown() throws IOException {
		mockSocket.close();
		assertEquals(mockSocket.isClosed(),true);	
	}
}
