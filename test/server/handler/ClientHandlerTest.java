package server.handler;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.handler.ClientHandler;
import server.mocks.MockSocket;
import log.Logger;
import log.mocks.StringLogger;



public class ClientHandlerTest {
	private ClientHandler handler;
	private MockSocket mockSocket;
	private OutputStream out;
	private OutputStream errorOut;
	private Logger logger;
	
	private final String CRLF = "\r\n";

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
		String statusLine = "HTTP/1.1 200 OK" + CRLF;		
		mockSocket = new MockSocket("localhost",5000,request.getBytes());
		handler = new ClientHandler(mockSocket,new StringLogger());
		handler.run();
		assertEquals(mockSocket.getOutputMock(), statusLine);	
	}
	
	@Test
	public void testLogger() throws IOException{
		String request = "GET /test.html HTTP/1.1";
		String statusLine = "HTTP/1.1 200 OK" + CRLF;		
		mockSocket = new MockSocket("localhost",5000,request.getBytes());
		Logger logger = new StringLogger();
		handler = new ClientHandler(mockSocket, logger);
		handler.run();
		assertEquals(((StringLogger)logger).logs.get(0), "GET /test.html HTTP/1.1");
		assertEquals(((StringLogger)logger).logs.get(1), "HTTP/1.1 200 OK" + CRLF
														+ "Server: Kristin Server" + CRLF
														+ "Accept-Ranges: bytes" + CRLF
														+ "Content-Type: text/html" + CRLF + CRLF
														+"<!doctype html><html><head><title>"
														+ "Test at root</title></head><body>"
														+ "Test at root</body></html>");
	}
	
	@After
	public void tearDown() throws IOException {
		mockSocket.close();
		assertEquals(mockSocket.isClosed(),true);	
	}
}
