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
import server.socket.WireServerSocket;
import server.mocks.MockServerSocketWrapper;
import server.mocks.MockServerSocket;
import server.mocks.MockSocket;


public class ServerTest {
	
	ServerSocketService service;
	int port;
	String document;
	MockServerSocket mockSSocket;
	
	
	@Before
	public void setUp() throws IOException {
		port = 5000;
		document = "PUBLIC_DIR";
		mockSSocket = new MockServerSocket(port);
	}

	@After
	public void tearDown() throws IOException {
		try {
			if(mockSSocket !=null ){
				mockSSocket.close();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testServerConstructed() throws Exception {
		Server server = new Server(mockSSocket,port,document);
		assertEquals(document, "PUBLIC_DIR");
		assertEquals(port, 5000);
		assertEquals(mockSSocket.getClass().getName(), "server.mocks.MockServerSocket");
		assertEquals(server.getClass().getName(),"server.Server");
	}
	
	@Test
	public void testServerStart() throws Exception {
		ServerSocket serverSocket = new ServerSocket(1234);
		service = new WireServerSocket(serverSocket);
		Server server = new Server(service,port,document);
		ServerStarter starter = new ServerStarter(server);
		starter.start();
		
		serverSocket.close();
		assertEquals(service.isClosed(),true);
		assertEquals(service.getClass().getName(),"server.socket.WireServerSocket");
		
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
