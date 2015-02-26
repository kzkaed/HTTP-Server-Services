package server;

import org.junit.*;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;

import log.Logger;
import log.mocks.StringLogger;
import server.Server;
import server.request.ParametersParser;
import server.request.ParametersParserURL;
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
	public void testLogServerStatus() throws IOException, URISyntaxException{
		mockSSocket.closed = true;
		Logger logger = new StringLogger();
		Server server = new Server(mockSSocket,port,document, logger);
		server.start();
		assertEquals(((StringLogger)logger).logs.get(0), "Server Starting...");
	}
	
	@Test
	public void testServerStart() throws IOException, URISyntaxException {
		mockSSocket.closed = true;
		Logger logger = new StringLogger();
		Server server = new Server(mockSSocket,port,document, logger);
		server.start();
		
		assertEquals(mockSSocket.isClosed(),true);
	}
	
	
	@Test
	public void test() throws MalformedURLException, URISyntaxException, UnknownHostException {
		
		String host = InetAddress.getLoopbackAddress().getHostName().toString();
		String test = InetAddress.getLocalHost().getHostName().toString();
		System.out.println(host + test);
		URL url = new URL("http",host,5000, "/test/index");//"http://localhost:5000/test/index?name=kristin#1"
		String userInfo = null;
		String path = "/test/index"; 
		String query = "name=kristin";
		String fragment = "1";
		URI uri = new URI("http",userInfo,host,5000,path,query,fragment);
		
		System.out.println("url " + url);
		System.out.println("uri " + uri);
		ParametersParser paramsParser = new ParametersParserURL(url.toString());
		assertEquals(paramsParser.getFilename(),"/test/index");
		
		
	}

	

	
	class ServerStarter extends Thread {
        public Server server;
        
        public void run() {
        	try {
				server.start();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
        
        public ServerStarter(Server mServer) {
            this.server = mServer;
        }
    }
		
}
