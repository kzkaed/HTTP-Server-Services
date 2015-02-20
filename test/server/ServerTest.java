package server;

import org.junit.*;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.ServerSocket;

import log.Logger;
import log.mocks.StringLogger;
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
	public void testLogServerStatus() throws IOException{
		mockSSocket.closed = true;
		Logger logger = new StringLogger();
		Server server = new Server(mockSSocket,port,document, logger);
		server.start();
		assertEquals(((StringLogger)logger).logs.get(0), "Server Starting...");
	}
	
	@Test
	public void testServerStart() throws IOException {
		mockSSocket.closed = true;
		Logger logger = new StringLogger();
		Server server = new Server(mockSSocket,port,document, logger);
		server.start();
		
		assertEquals(mockSSocket.isClosed(),true);
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
