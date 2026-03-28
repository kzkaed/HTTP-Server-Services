package server.response;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import routes.AssetManager;
import server.request.Request;
import server.response.assets.DirectoryAsset;
import server.response.assets.DynamicAsset;
import server.response.assets.FileNotFound;
import server.response.assets.StaticAsset;
import server.response.assets.ImageAsset;
import server.response.assets.Options;
import server.response.assets.Parameter;
import server.response.assets.Post;
import server.response.assets.Put;
import server.response.assets.StaticPathExt;
import views.HtmlViewFactory;

@DisplayName("ResponseBuilder")
public class ResponseBuilderTest {

	private final String CRLF = "\r\n";
	private final String HEADERS_END = CRLF + CRLF;
	AssetManager manager;

	@BeforeEach
	public void setUp(){
		manager = new AssetManager();
		HtmlViewFactory viewFactory = new HtmlViewFactory();
		manager.register(new StaticAsset("public"));
		manager.register(new DynamicAsset(viewFactory));
		manager.register(new DirectoryAsset("public", viewFactory));
		manager.register(new Parameter(viewFactory));

		manager.register(new ImageAsset("public"));
		manager.register(new StaticPathExt("public"));
		manager.register(new Options());
		manager.register(new Post());
		manager.register(new Put());
	}

	@Nested
	@DisplayName("when handling write requests")
	class WhenHandlingWriteRequests {

		@Test
		@DisplayName("returns 200 OK for a POST request")
		void returns_200_ok_for_a_post_request() throws IOException {
			String requestLine = "POST / HTTP/1.1";
			Request request = new Request("POST","/","HTTP/1.1",null,null,requestLine,null);
			ResponseBuilder responseBuilder = new ResponseBuilder(request);

			Response response = responseBuilder.buildResponse(manager);
			assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK" + CRLF, "POST should return 200 OK status line");
		}

		@Test
		@DisplayName("returns 200 OK for a PUT request")
		void returns_200_ok_for_a_put_request() throws IOException {
			String requestLine = "PUT / HTTP/1.1";
			Request request = new Request("POST","/","HTTP/1.1",null,null,requestLine,null);
			ResponseBuilder responseBuilder = new ResponseBuilder(request);
			Response response = responseBuilder.buildResponse(manager);
			assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK" + CRLF, "PUT should return 200 OK status line");
		}
	}

	@Nested
	@DisplayName("when handling OPTIONS requests")
	class WhenHandlingOptionsRequests {

		@Test
		@DisplayName("returns allowed methods in the response")
		void returns_allowed_methods_in_the_response() throws IOException {
			String requestLine = "OPTIONS / HTTP/1.1";
			Request request = new Request("OPTIONS","/","HTTP/1.1",null,null,requestLine,null);
			ResponseBuilder responseBuilder = new ResponseBuilder(request);
			Response response = responseBuilder.buildResponse(manager);
			assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT" + CRLF + CRLF, response.getResponseAsString(), "OPTIONS should list allowed methods");
		}
	}

	@Nested
	@DisplayName("when handling GET requests")
	class WhenHandlingGetRequests {

		@Test
		@DisplayName("returns content for a routed static path")
		void returns_content_for_a_routed_static_path() throws IOException {
			String requestLine = "GET /test/static HTTP/1.1";
			Request request = new Request("GET","/test/static","HTTP/1.1",null,null,requestLine,null);
			ResponseBuilder responseBuilder = new ResponseBuilder(request);
			Response response = responseBuilder.buildResponse(manager);
			String responseExpected = "HTTP/1.1 200 OK" + CRLF
			+"Server: Kristin Server" + CRLF
			+"Content-Type: text/html" + HEADERS_END
			+"<!doctype html><html><head></head><body>Test Static</body></html>";
			assertEquals(response.getResponseAsString(), responseExpected, "GET /test/static should return full HTML response");

		}

		@Test
		@DisplayName("returns 404 when the file is not found")
		void returns_404_when_the_file_is_not_found() throws IOException {
			String requestLine = "GET /jam HTTP/1.1";
			Request request = new Request("GET","/jam","HTTP/1.1",null,null,requestLine,null);
			ResponseBuilder responseBuilder = new ResponseBuilder(request);
			Response responseReceived = responseBuilder.buildResponse(manager);
			String response = "HTTP/1.1 404 Not Found" + CRLF
					+ "Server: Kristin Server" + CRLF
					+ "Content-Type: text/html" + HEADERS_END
					+ "Not Found";

			assertEquals(response, responseReceived.getResponseAsString(), "unknown path should return 404 Not Found");
		}
	}

	/*@Test
	public void test502NotImplemented() throws IOException{
		Request request = new Request("","","",null,null,"",null);
		ResponseBuilder responseBuilder = new ResponseBuilder(request);
		Response responseReceived = responseBuilder.buildResponse(manager);
		String response = "HTTP/1.1 502 Not Implemented" + CRLF
				+ "Server: Kristin Server" + CRLF
				+ "Accept-Ranges: bytes" + CRLF
				+ "Content-Type: text/html" + HEADERS_END
				+ "502 Not Implemented";

		assertEquals(response, responseReceived.getResponseAsString());
	}*/

}
