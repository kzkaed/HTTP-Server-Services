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
import views.HtmlViewFactory;

@DisplayName("DynamicPathExt")
public class DynamicPathExtTest {
	private Request request;
	private Asset dynamic;

	@BeforeEach
	public void setUp() throws Exception {
		dynamic = new DynamicPathExt(new HtmlViewFactory());
		request = new Request();
	}

	@Nested
	@DisplayName("when checking if it can handle a request")
	class WhenCheckingIfItCanHandleARequest {

		@Test
		@DisplayName("returns false for a non-matching path")
		void returns_false_for_a_non_matching_path() {
			request.setMethod("GET");
			request.setURI("/other");
			assertFalse(dynamic.canHandle(request), "should not handle an unregistered path");
		}

		@Test
		@DisplayName("returns true for a matching dynamic path")
		void returns_true_for_a_matching_dynamic_path() {
			request.setMethod("GET");
			request.setURI("/test/dynamic");
			assertTrue(dynamic.canHandle(request), "should handle /test/dynamic");
		}
	}

	@Nested
	@DisplayName("when executing the route")
	class WhenExecutingTheRoute {

		@Test
		@DisplayName("returns the URI rendered in the response body")
		void returns_the_uri_rendered_in_the_response_body() throws MalformedURLException, UnsupportedEncodingException {
			request.setMethod("GET");
			request.setURI("/test/dynamic");
			Response response = dynamic.execute(request);
			assertEquals("<!doctype html><html><head></head><body>/test/dynamic</body></html>", response.getBody(), "body should contain the requested URI");
		}
	}
}
