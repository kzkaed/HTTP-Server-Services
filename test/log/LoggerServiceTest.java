package log;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;

import log.LoggerService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoggerServiceTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private int port;
	private String path;
	ServerSocket serverSocket;

	@Before
	public void setUp() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent)); 
	    path = "PUBLIC_DIR";
	    port = 1235;
	    try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws IOException{
		System.setOut(null);
	    System.setErr(null);
		serverSocket.close();
	}

	@Test
	public void out() {
	    System.out.print("Port " + port);
	    assertEquals("Port 1235", outContent.toString());
	}

	@Test
	public void err() {
	    System.err.print("PUBLIC DIR: " + path);
	    assertEquals("PUBLIC DIR: PUBLIC_DIR", errContent.toString());
	}
	
	@Test
	public void testDisplayServerStatusTest() throws IOException{
		LoggerService.displayServerStatus(serverSocket, port, path);
		assertEquals("Server Starting..." 
		+"\nServerSocket[addr=0.0.0.0/0.0.0.0,port=0,localport=1235]"
		+"\nPort: 1235"
		+"\nDOCUMENT ROOTPUBLIC_DIR\n",outContent.toString());
	}
	
	@Test
	public void testDisplayServerStatusTestwithNull() throws IOException{
		path = "PUBLIC_DIR";
		ServerSocket serverSocket = null;
		LoggerService.displayServerStatus(serverSocket, port, path);
		assertEquals("Server Starting..." 
		+"\nnull"
		+"\nPort: 1235"
		+"\nDOCUMENT ROOTPUBLIC_DIR\n",outContent.toString());	
	}
	
	@Test
	public void testDisplayRequest() {
		LoggerService.displayRequest("GET / HTTP/1.1");
		assertEquals("Processing request GET / HTTP/1.1\n",outContent.toString());
	}
	
	@Test
	public void testDisplayError() {
		LoggerService.displayError("IOException ioe");
		assertEquals("IOException ioe\n",errContent.toString());
	}
	
	@Test
	public void testDisplayInfo() {
		LoggerService.displayInfo("the content");
		assertEquals("the content\n",outContent.toString());
	}
	

}
