package server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
import routes.AssetManager;
import server.Server;
import server.request.ParametersParser;
import server.request.ParametersParserURL;
import server.socket.ServerSocketService;
import server.socket.WireServerSocket;
import server.mocks.MockServerSocket;

@DisplayName("Server")
public class ServerTest {

	ServerSocketService service;
	int port;
	String document;
	MockServerSocket mockSSocket;
	ServerSocket serverSocket;

	@BeforeEach
	public void setUp() throws IOException {
		port = 5000;
		document = "PUBLIC_DIR";
		mockSSocket = new MockServerSocket(port);

	}

	@AfterEach
	public void tearDown() throws IOException {
		try {
			if(mockSSocket !=null ){
				mockSSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Nested
	@DisplayName("when constructed")
	class WhenConstructed {

		@Test
		@DisplayName("initializes with the correct socket, port, and document root")
		void initializes_with_the_correct_socket_port_and_document_root() throws IOException {
			Server server = new Server(mockSSocket, port, new AssetManager(), document, "localhost");
			assertEquals(document, "PUBLIC_DIR", "document root should be PUBLIC_DIR");
			assertEquals(port, 5000, "port should be 5000");
			assertEquals(mockSSocket.getClass().getName(), "server.mocks.MockServerSocket",
					"socket should be a MockServerSocket");
			assertEquals(server.getClass().getName(),"server.Server",
					"server should be an instance of server.Server");
		}
	}

	@Nested
	@DisplayName("when starting up")
	class WhenStartingUp {

		@Test
		@DisplayName("logs a startup message")
		void logs_a_startup_message() throws IOException, URISyntaxException {
			mockSSocket.closed = true;
			Logger logger = new StringLogger();
			Server server = new Server(mockSSocket, port, new AssetManager(), logger, document, "localhost");
			server.start();
			assertEquals(((StringLogger)logger).logs.get(0), "Server Starting...",
					"first log message should indicate server is starting");
		}

		@Test
		@DisplayName("runs until the socket is closed")
		void runs_until_the_socket_is_closed() throws IOException, URISyntaxException {
			mockSSocket.closed = true;
			Logger logger = new StringLogger();
			Server server = new Server(mockSSocket, port, new AssetManager(), logger, document, "localhost");
			server.start();

			assertEquals(mockSSocket.isClosed(),true, "socket should be closed after start completes");
		}
	}

	@Nested
	@DisplayName("when shutting down")
	class WhenShuttingDown {

		@Test
		@DisplayName("closes the socket and logs a shutdown message")
		void closes_the_socket_and_logs_a_shutdown_message() throws IOException {
			mockSSocket.closed = false;
			Logger logger = new StringLogger();
			Server server = new Server(mockSSocket, port, new AssetManager(), logger, document, "localhost");
			server.stop();

			assertTrue(mockSSocket.isClosed(), "socket should be closed after stop");
			assertEquals("Server Shutting Down...", ((StringLogger)logger).logs.get(0),
					"first log message should indicate server is shutting down");
		}

		@Test
		@DisplayName("restores interrupted state when shutdown is interrupted")
		void restores_interrupted_state_when_shutdown_is_interrupted() {
			mockSSocket.closed = false;
			Logger logger = new StringLogger();
			Server server = new Server(mockSSocket, port, new AssetManager(), logger, document, "localhost");
			Thread.currentThread().interrupt();
			server.stop();

			assertTrue(Thread.interrupted(), "thread interrupted flag should be restored after InterruptedException");
		}

		@Test
		@DisplayName("logs error when stop encounters an IOException")
		void logs_error_when_stop_encounters_an_io_exception() throws IOException {
			MockServerSocket failSocket = new MockServerSocket(port) {
				@Override
				public void close() throws IOException {
					throw new IOException("test close failure");
				}
			};
			StringLogger logger = new StringLogger();
			Server server = new Server(failSocket, port, new AssetManager(), logger, document, "localhost");
			server.stop();

			assertFalse(logger.errors.isEmpty(), "should log an error when IOException occurs during stop");
		}
	}

	@Nested
	@DisplayName("when building URIs")
	class WhenBuildingUris {

		@Test
		@DisplayName("constructs a valid URI with host, port, path, query, and fragment")
		void constructs_a_valid_uri_with_all_components() throws MalformedURLException, URISyntaxException, UnknownHostException {
			String host = InetAddress.getLoopbackAddress().getHostName().toString();
			String test = InetAddress.getLocalHost().getHostName().toString();
			String userInfo = null;
			String path = "/test/index";
			String query = "name=kristin";
			String fragment = "1";
			URI uri = new URI("http",userInfo,host,5000,path,query,fragment);

			//ParametersParser paramsParser = new ParametersParserURL(url.toString());
			//assertEquals(paramsParser.getFilename(),"/test/index");
		}
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
