package server;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;

import log.LoggerService;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoggerServiceTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private int port;
	private String path;

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent)); 
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}

	@Test
	public void out() {
		int port = 5000;
	    System.out.print("Port " + port);
	    assertEquals("Port 5000", outContent.toString());
	}

	@Test
	public void err() {
		path = "PUBLIC_DIR";
	    System.err.print("PUBLIC DIR: " + path);
	    assertEquals("PUBLIC DIR: PUBLIC_DIR", errContent.toString());
	}
	
	@Test
	public void displayServerStatusTest() throws IOException{
		path = "PUBLIC_DIR";
		int port = 5000;
		ServerSocket serverSocket = new ServerSocket(port);
		LoggerService.displayServerStatus(serverSocket, port, path);
		assertEquals("Server Starting..." 
		+"\nServerSocket[addr=0.0.0.0/0.0.0.0,port=0,localport=5000]"
		+"\nPort: 5000"
		+"\nDOCUMENT ROOTPUBLIC_DIR\n",
outContent.toString());
		serverSocket.close();
	}
	
	@Test
	public void displayServerStatusTestwithNull() throws IOException{
		path = "PUBLIC_DIR";
		int port = 5000;
		ServerSocket serverSocket = null;
		LoggerService.displayServerStatus(serverSocket, port, path);
		assertEquals("Server Starting..." 
		+"\nnull"
		+"\nPort: 5000"
		+"\nDOCUMENT ROOTPUBLIC_DIR\n",
outContent.toString());
		
	}
	
	

}
