package server;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;

import server.Server;
import server.socket.ServerSocketService;
import server.socket.WireServerSocketWrapper;
import server.mocks.MockServerSocketWrapper;
import server.mocks.MockServerSocket;
import server.mocks.MockSocket;


public class ServerTest {
	
	ServerSocket serverSocket;
	int port;
	String document;
	MockServerSocket mockSSocket;
	
	
	@Before
	public void setUp() throws IOException {
		port = 5000;
		document = "PUBLIC_DIR";
		mockSSocket = new MockServerSocket(port);
		serverSocket = new ServerSocket(port);
	}

	@After
	public void tearDown() throws IOException {
		try {
			if(mockSSocket !=null ){
				mockSSocket.close();
				
			}
			if (!serverSocket.isClosed()){
				serverSocket.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	@Test
	public void testServerStart() throws Exception {
		

		Server server = new Server(serverSocket,port,document);
		ServerStarter starter = new ServerStarter(server);
		starter.start();
		assertEquals(serverSocket.getClass().getName(), "java.net.ServerSocket");
		assertTrue(serverSocket.isBound());
		assertEquals(document, "PUBLIC_DIR");
		assertEquals(port, 5000);
		serverSocket.close();
		
		assertEquals(serverSocket.isClosed(),true);
		
		
	}
	
	
	
	class ServerStarter extends Thread {
        public Server server;
        
        public ServerStarter(Server mServer) {
            this.server = mServer;
        }

        public void run() {
        	try {
				server.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
    
        }
           
    }
		
}
