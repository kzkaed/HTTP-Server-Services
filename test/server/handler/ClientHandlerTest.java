package server.handler;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import routes.AssetManager;
import routes.MockAsset;
import server.handler.ClientHandler;
import server.mocks.MockSocket;
import server.request.Request;
import server.response.assets.StaticAsset;
import log.Logger;
import log.mocks.StringLogger;

@DisplayName("ClientHandler")
public class ClientHandlerTest {
	private ClientHandler handler;
	private MockSocket mockSocket;
	private OutputStream out;
	private OutputStream errorOut;
	private Logger logger;
	private AssetManager assetManager;

	private final String CRLF = "\r\n";

	@BeforeEach
	public void setUp() throws IOException {
		this.out = new ByteArrayOutputStream();
		this.errorOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		System.setErr(new PrintStream(errorOut));
		this.logger = new StringLogger();
		this.assetManager = new AssetManager();
	}

	@Nested
	@DisplayName("when processing a request")
	class WhenProcessingARequest {

		@Test
		@DisplayName("returns an HTTP 200 status line for a valid GET request")
		void returns_an_http_200_status_line_for_a_valid_get_request() throws IOException {
			String request = "GET /test.html HTTP/1.1";
			String statusLine = "HTTP/1.1 200 OK" + CRLF;
			mockSocket = new MockSocket("localhost",5000,request.getBytes());
			handler = new ClientHandler(mockSocket, logger, assetManager, "localhost", 5000);
			handler.run();
			assertEquals(mockSocket.getOutputMock(), statusLine, "should return a 200 OK status line");
		}

		@Test
		@DisplayName("parses the request into a Request object with correct fields")
		void parses_the_request_into_a_request_object_with_correct_fields() throws IOException {
			String requestLine = "GET /test.html HTTP/1.1";
			mockSocket = new MockSocket("localhost",5000,requestLine.getBytes());
			handler = new ClientHandler(mockSocket, logger, assetManager, "localhost", 5000);

			Request request = new Request("GET", "/test.html","HTTP/1.1",null,"GET /test.html HTTP/1.1",null,null);

			handler.run();
			assertEquals(request.getClass(), handler.getRequest().getClass(), "request class should match");
			assertEquals(request.getMethod(),handler.getRequest().getMethod(), "request method should match");
			assertEquals(request.getURI(),handler.getRequest().getURI(), "request URI should match");
		}
	}

	@Nested
	@DisplayName("when logging")
	class WhenLogging {

		@Test
		@DisplayName("logs both the request and the full response")
		void logs_both_the_request_and_the_full_response() throws IOException {
			String request = "GET /text-file.txt HTTP/1.1";
			mockSocket = new MockSocket("localhost",5000,request.getBytes());
			logger = new StringLogger();
			handler = new ClientHandler(mockSocket, logger, assetManager, "localhost", 5000);
			assetManager.register(new StaticAsset("public"));
			String loggedRequest = "GET /text-file.txt HTTP/1.1";
			String loggedResponse = "HTTP/1.1 200 OK" + CRLF
					+ "Server: Kristin Server" + CRLF
					+ "Content-Type: text/html" + CRLF + CRLF
					+"file1 contents";

			handler.run();
			assertEquals(loggedRequest, ((StringLogger)logger).logs.get(0), "logged request should match");
			assertEquals(loggedResponse, ((StringLogger)logger).logs.get(1), "logged response should match");
		}
	}

	@AfterEach
	public void tearDown() throws IOException {
		mockSocket.close();
		assertEquals(mockSocket.isClosed(),true, "socket should be closed after each test");
	}
}
