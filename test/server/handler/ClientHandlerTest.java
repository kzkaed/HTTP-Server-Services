package server.handler;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import routes.AssetManager;
import routes.MockAsset;
import server.handler.ClientHandler;
import server.mocks.MockSocket;
import server.request.Request;
import server.response.assets.StaticAsset;
import log.Logger;
import log.mocks.StringLogger;

public class ClientHandlerTest {
	private ClientHandler handler;
	private MockSocket mockSocket;
	private OutputStream out;
	private OutputStream errorOut;
	private Logger logger;
	private AssetManager assetManager;
	
	private final String CRLF = "\r\n";

	@Before
	public void setUp() throws IOException {
		this.out = new ByteArrayOutputStream();
		this.errorOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		System.setErr(new PrintStream(errorOut));
		this.logger = new StringLogger();
		this.assetManager = new AssetManager();
	}
	
	@Test
	public void testRunProcessHTTPRequest() throws IOException{
		String request = "GET /test.html HTTP/1.1";
		String statusLine = "HTTP/1.1 200 OK" + CRLF;		
		mockSocket = new MockSocket("localhost",5000,request.getBytes());
		handler = new ClientHandler(mockSocket, logger, assetManager);
		handler.run();
		assertEquals(mockSocket.getOutputMock(), statusLine);	
	}
	
	
	@Test
	public void testLogger() throws IOException{
		String request = "GET /text-file.txt HTTP/1.1";		
		mockSocket = new MockSocket("localhost",5000,request.getBytes());
		logger = new StringLogger();
		handler = new ClientHandler(mockSocket, logger, assetManager);
		assetManager.register(new StaticAsset());
		String loggedRequest = "GET /text-file.txt HTTP/1.1";
		String loggedResponse = "HTTP/1.1 200 OK" + CRLF
				+ "Server: Kristin Server" + CRLF
				+ "Content-Type: text/html" + CRLF + CRLF
				+"file1 contents";
		
		handler.run();
		assertEquals(loggedRequest, ((StringLogger)logger).logs.get(0));
		assertEquals(loggedResponse, ((StringLogger)logger).logs.get(1));
	}
	
	@Test
	public void testRequestParsed() throws IOException{
		String requestLine = "GET /test.html HTTP/1.1";		
		mockSocket = new MockSocket("localhost",5000,requestLine.getBytes());
		handler = new ClientHandler(mockSocket, logger, assetManager);
	
		Request request = new Request("GET", "/test.html","HTTP/1.1",null,"GET /test.html HTTP/1.1",null,null);
		
		handler.run();
		assertEquals(request.getClass(), handler.getRequest().getClass());
		assertEquals(request.getMethod(),handler.getRequest().getMethod());
		assertEquals(request.getURI(),handler.getRequest().getURI());
	}
	

	@After
	public void tearDown() throws IOException {
		mockSocket.close();
		assertEquals(mockSocket.isClosed(),true);	
	}
}
