package server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import log.mocks.StringLogger;
import routes.AssetManager;
import server.mocks.MockServerSocket;
import server.mocks.MockSocket;
import server.socket.ServerSocketService;
import server.socket.SocketService;

@DisplayName("Server")
public class ServerSpec {

	private int port = 5000;
	private String publicDir = "public";
	private StringLogger logger;

	@BeforeEach
	void setUp() {
		logger = new StringLogger();
		Context.setContext(port, publicDir);
	}

	@Nested
	@DisplayName("when starting up")
	class WhenStartingUp {

		@Test
		@DisplayName("logs a startup message")
		void logs_a_startup_message() throws Exception {
			MockServerSocket mock = new MockServerSocket(port);
			mock.closed = true;

			Server server = new Server(mock, port, new AssetManager(), logger, publicDir, "localhost");
			server.start();

			assertEquals("Server Starting...", logger.logs.get(0));
		}
	}

	@Nested
	@DisplayName("when serving requests")
	class WhenServingRequests {

		@Test
		@DisplayName("returns file contents for a valid static file")
		void returns_file_contents_for_a_valid_static_file() throws Exception {
			MockSocket clientSocket = new MockSocket("localhost", port, "GET /file1 HTTP/1.1".getBytes());
			SingleRequestServerSocket mock = new SingleRequestServerSocket(clientSocket);

			Server server = new Server(mock, port, new AssetManager(), logger, publicDir, "localhost");
			Thread serverThread = new Thread(server::start);
			serverThread.start();

			Thread.sleep(200);
			server.stop();
			serverThread.join(2000);

			String output = ((ByteArrayOutputStream) clientSocket.getOutputStream()).toString();
			assertTrue(output.contains("200 OK"), "expected 200 OK in response");
			assertTrue(output.contains("file1 contents"), "expected file body in response");
		}

		@Test
		@DisplayName("returns 404 for a nonexistent file")
		void returns_404_for_a_nonexistent_file() throws Exception {
			MockSocket clientSocket = new MockSocket("localhost", port, "GET /no-such-file.html HTTP/1.1".getBytes());
			SingleRequestServerSocket mock = new SingleRequestServerSocket(clientSocket);

			Server server = new Server(mock, port, new AssetManager(), logger, publicDir, "localhost");
			Thread serverThread = new Thread(server::start);
			serverThread.start();

			Thread.sleep(200);
			server.stop();
			serverThread.join(2000);

			String output = ((ByteArrayOutputStream) clientSocket.getOutputStream()).toString();
			assertTrue(output.contains("404"), "expected 404 in response");
		}

		@Test
		@DisplayName("handles multiple concurrent connections")
		void handles_multiple_concurrent_connections() throws Exception {
			int requestCount = 3;
			List<MockSocket> clients = new ArrayList<>();
			for (int i = 0; i < requestCount; i++) {
				clients.add(new MockSocket("localhost", port, "GET /file1 HTTP/1.1".getBytes()));
			}
			QueuedServerSocket mock = new QueuedServerSocket(clients);

			Server server = new Server(mock, port, new AssetManager(), logger, publicDir, "localhost");
			Thread serverThread = new Thread(server::start);
			serverThread.start();

			Thread.sleep(500);
			server.stop();
			serverThread.join(2000);

			for (MockSocket client : clients) {
				String output = ((ByteArrayOutputStream) client.getOutputStream()).toString();
				assertTrue(output.contains("200 OK"), "each client should receive 200 OK");
			}
		}

		@Test
		@DisplayName("logs the request and response for each connection")
		void logs_the_request_and_response() throws Exception {
			MockSocket clientSocket = new MockSocket("localhost", port, "GET /file1 HTTP/1.1".getBytes());
			SingleRequestServerSocket mock = new SingleRequestServerSocket(clientSocket);

			Server server = new Server(mock, port, new AssetManager(), logger, publicDir, "localhost");
			Thread serverThread = new Thread(server::start);
			serverThread.start();

			Thread.sleep(200);
			server.stop();
			serverThread.join(2000);

			boolean loggedRequest = false;
			boolean loggedResponse = false;
			for (String log : logger.logs) {
				if (log.contains("GET /file1")) loggedRequest = true;
				if (log.contains("200 OK")) loggedResponse = true;
			}
			assertTrue(loggedRequest, "should log the request line");
			assertTrue(loggedResponse, "should log the response");
		}
	}

	@Nested
	@DisplayName("when shutting down")
	class WhenShuttingDown {

		@Test
		@DisplayName("logs a shutdown message")
		void logs_a_shutdown_message() throws Exception {
			MockServerSocket mock = new MockServerSocket(port);
			Server server = new Server(mock, port, new AssetManager(), logger, publicDir, "localhost");

			server.stop();

			assertEquals("Server Shutting Down...", logger.logs.get(0));
		}

		@Test
		@DisplayName("closes the server socket")
		void closes_the_server_socket() throws Exception {
			MockServerSocket mock = new MockServerSocket(port);
			mock.closed = false;
			Server server = new Server(mock, port, new AssetManager(), logger, publicDir, "localhost");

			server.stop();

			assertTrue(mock.isClosed());
		}

		@Test
		@DisplayName("stops cleanly while waiting for connections")
		void stops_cleanly_while_waiting_for_connections() throws Exception {
			BlockingServerSocket mock = new BlockingServerSocket();
			Server server = new Server(mock, port, new AssetManager(), logger, publicDir, "localhost");

			Thread serverThread = new Thread(server::start);
			serverThread.start();
			Thread.sleep(100);

			server.stop();
			serverThread.join(2000);

			assertTrue(mock.isClosed(), "server socket should be closed");
			assertFalse(serverThread.isAlive(), "server thread should have exited");
		}
	}

	// -- Test helpers --

	static class BlockingServerSocket implements ServerSocketService {
		private volatile boolean closed = false;

		@Override
		public SocketService accept() throws IOException {
			while (!closed) {
				try { Thread.sleep(10); } catch (InterruptedException e) { break; }
			}
			throw new IOException("closed");
		}

		@Override
		public void close() throws IOException { closed = true; }

		@Override
		public boolean isClosed() { return closed; }

		@Override
		public boolean isBound() { return true; }
	}

	static class SingleRequestServerSocket implements ServerSocketService {
		private final SocketService socket;
		private boolean served = false;
		private volatile boolean closed = false;

		SingleRequestServerSocket(SocketService socket) {
			this.socket = socket;
		}

		@Override
		public SocketService accept() throws IOException {
			if (served) {
				while (!closed) {
					try { Thread.sleep(10); } catch (InterruptedException e) { break; }
				}
				throw new IOException("closed");
			}
			served = true;
			return socket;
		}

		@Override
		public void close() throws IOException { closed = true; }

		@Override
		public boolean isClosed() { return closed; }

		@Override
		public boolean isBound() { return true; }
	}

	static class QueuedServerSocket implements ServerSocketService {
		private final List<MockSocket> clients;
		private int index = 0;
		private boolean closed = false;
		final CountDownLatch allServed;

		QueuedServerSocket(List<MockSocket> clients) {
			this.clients = clients;
			this.allServed = new CountDownLatch(clients.size());
		}

		@Override
		public synchronized SocketService accept() throws IOException {
			if (closed || index >= clients.size()) {
				closed = true;
				throw new IOException("closed");
			}
			MockSocket client = clients.get(index++);
			allServed.countDown();
			return client;
		}

		@Override
		public void close() throws IOException { closed = true; }

		@Override
		public boolean isClosed() { return closed; }

		@Override
		public boolean isBound() { return true; }
	}
}
