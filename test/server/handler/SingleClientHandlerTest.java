package server.handler;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import log.LoggerService;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.handler.SingleClientHandler;
import server.mocks.MockSocket;



public class SingleClientHandlerTest {
	private SingleClientHandler handler;
	private Socket socket;
	private MockSocket mockSocket;


	@Before
	public void setUp() throws IOException {	
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
	
	@After
	public void tearDown() throws IOException {
		mockSocket.close();
		assertEquals(mockSocket.isClosed(),true);	
	}
}
