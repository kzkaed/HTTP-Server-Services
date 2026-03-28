package server.response.assets;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import server.request.Request;
import server.response.Response;

@DisplayName("StaticPathExt")
public class StaticPathExtTest {

	private StaticPathExt testStatic;
	private Request request;

	@BeforeEach
	public void setUp() throws Exception {
		testStatic = new StaticPathExt("public");
		request = new Request();
		request.setMethod("GET");
		request.setURI("/test/static");
	}

	@Nested
	@DisplayName("when checking if it can handle a request")
	class WhenCheckingIfItCanHandleARequest {

		@Test
		@DisplayName("returns true for a registered static path")
		void returns_true_for_a_registered_static_path() {
			assertTrue(testStatic.canHandle(request), "should handle /test/static");
		}
	}

	@Nested
	@DisplayName("when executing the route")
	class WhenExecutingTheRoute {

		@Test
		@DisplayName("returns the static file content in the response body")
		void returns_the_static_file_content_in_the_response_body() throws MalformedURLException, UnsupportedEncodingException {
			Response response = testStatic.execute(request);

			assertEquals("<!doctype html><html><head></head><body>Test Static</body></html>", response.getBody(), "body should contain the static HTML content");
		}
	}
}
