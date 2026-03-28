package server.socket;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("WireServerSocket")
public class WireServerSocketTest {
	WireServerSocket service;
	ServerSocket serverSocket;

	@BeforeEach
	public void setUp() throws Exception {
		serverSocket = new ServerSocket(1357);
	}

	@Nested
	@DisplayName("when initialized with a server socket")
	class WhenInitializedWithAServerSocket {

		@Test
		@DisplayName("reports the correct port and closes cleanly")
		void reports_the_correct_port_and_closes_cleanly() throws IOException {
			service = new WireServerSocket(serverSocket);
			assertEquals(1357,service.getPort(), "port should match the one provided to the server socket");
			service.close();
			assertEquals(service.isClosed(), true, "service should be closed");
			serverSocket.close();
			assertEquals(serverSocket.isClosed(),true, "underlying server socket should be closed");
		}
	}
}
