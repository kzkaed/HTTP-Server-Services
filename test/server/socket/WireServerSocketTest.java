package server.socket;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class WireServerSocketTest {
	WireServerSocket service;
	ServerSocket serverSocket;
	@Before
	public void setUp() throws Exception {
		serverSocket = new ServerSocket(1357);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPortIsSetForServerSocketService() throws IOException {
		service = new WireServerSocket(serverSocket);
		assertEquals(1357,service.getPort());
		service.close();
		assertEquals(service.isClosed(), true);
		serverSocket.close();
		assertEquals(serverSocket.isClosed(),true);
	}

}
