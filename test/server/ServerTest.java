package server;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import server.Server;
import server.socket.ServerSocketService;
import server.socket.WireServerSocket;
import server.mocks.MockServerSocket;

public class ServerTest {
	
	ServerSocketService service;
	int port;
	String document;
	MockServerSocket mockSSocket;
	ServerSocket serverSocket;
	
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
	public void testServerConstructed() throws IOException {
		Server server = new Server(mockSSocket,port,document);
		assertEquals(document, "PUBLIC_DIR");
		assertEquals(port, 5000);
		assertEquals(mockSSocket.getClass().getName(), "server.mocks.MockServerSocket");
		assertEquals(server.getClass().getName(),"server.Server");
	}
	
	@Test
	public void testServerStart() throws IOException {
		serverSocket = new ServerSocket(port);
		
		service = new WireServerSocket(serverSocket);
		Server server = new Server(service,port,document);
		
		ServerStarter starter = new ServerStarter(server);
		//starter.run();
		
		serverSocket.close();
		assertEquals(service.isClosed(),true);
		assertEquals(service.getClass().getName(),"server.socket.WireServerSocket");	
	}
	
	
	
	class ServerStarter extends Thread {
        public Server server;
        
        public void run() {
        	server.start();	
		}
        
        public ServerStarter(Server mServer) {
            this.server = mServer;
        }

        
           
    }
		
}
