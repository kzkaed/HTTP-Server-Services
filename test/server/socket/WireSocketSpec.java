package server.socket;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@DisplayName("WireSocket")
public class WireSocketSpec {

	private ServerSocket serverSocket;
	private Socket rawClient;
	private Socket rawServer;
	private WireSocket wireSocket;

	@BeforeEach
	void setUp() throws IOException {
		// Create a real TCP connection on localhost
		// ServerSocket listens, client connects, server accepts
		serverSocket = new ServerSocket(0); // port 0 = OS picks a free port
		rawClient = new Socket("localhost", serverSocket.getLocalPort());
		rawServer = serverSocket.accept();
		wireSocket = new WireSocket(rawServer);
	}

	@AfterEach
	void tearDown() throws IOException {
		rawClient.close();
		rawServer.close();
		serverSocket.close();
	}

	@Nested
	@DisplayName("when reading from the input stream")
	class WhenReading {

		@Test
		@DisplayName("receives bytes sent by the client")
		void receives_bytes_sent_by_the_client() throws IOException {
			// Client sends bytes through its output stream
			rawClient.getOutputStream().write("GET / HTTP/1.1".getBytes());
			rawClient.getOutputStream().flush();

			// WireSocket reads them from its input stream
			InputStream in = wireSocket.getInputStream();
			byte[] buffer = new byte[14];
			int bytesRead = in.read(buffer);

			assertEquals(14, bytesRead);
			assertEquals("GET / HTTP/1.1", new String(buffer));
		}
	}

	@Nested
	@DisplayName("when writing to the output stream")
	class WhenWriting {

		@Test
		@DisplayName("sends bytes that the client receives")
		void sends_bytes_that_the_client_receives() throws IOException {
			// WireSocket writes bytes through its output stream
			OutputStream out = wireSocket.getOutputStream();
			out.write("HTTP/1.1 200 OK\r\n".getBytes());
			out.flush();

			// Client reads them from its input stream
			byte[] buffer = new byte[17];
			int bytesRead = rawClient.getInputStream().read(buffer);

			assertEquals(17, bytesRead);
			assertEquals("HTTP/1.1 200 OK\r\n", new String(buffer));
		}
	}

	@Nested
	@DisplayName("when managing the connection lifecycle")
	class WhenManagingConnection {

		@Test
		@DisplayName("reports open when the socket is active")
		void reports_open_when_active() {
			assertFalse(wireSocket.isClosed(), "new socket should be open");
		}

		@Test
		@DisplayName("reports closed after close is called")
		void reports_closed_after_close() throws IOException {
			wireSocket.close();
			assertTrue(wireSocket.isClosed(), "socket should be closed after close()");
		}
	}
}
