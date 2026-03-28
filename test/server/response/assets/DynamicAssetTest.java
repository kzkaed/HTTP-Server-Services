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
import server.response.assets.DynamicAsset;
import views.HtmlViewFactory;

@DisplayName("DynamicAsset")
public class DynamicAssetTest {
	private Request request;
	private Asset dynamicAsset;

	@BeforeEach
	public void setUp() throws Exception {
		dynamicAsset = new DynamicAsset(new HtmlViewFactory());
		request = new Request();
		request.setMethod("GET");
		request.setURI("showParams");
		request.setParameter("param1", "bachelard's poetics of space");
	}

	@Nested
	@DisplayName("when checking if it can handle a request")
	class WhenCheckingIfItCanHandleARequest {

		@Test
		@DisplayName("returns true for a matching dynamic route")
		void returns_true_for_a_matching_dynamic_route() {
			assertTrue(dynamicAsset.canHandle(request), "should handle the showParams route");
		}
	}

	@Nested
	@DisplayName("when executing the route")
	class WhenExecutingTheRoute {

		@Test
		@DisplayName("returns parameters rendered in the response body")
		void returns_parameters_rendered_in_the_response_body() throws MalformedURLException, UnsupportedEncodingException {
			Response response = dynamicAsset.execute(request);
			assertEquals("<!doctype html><html><head></head><body>param1:bachelard's poetics of space<br></body></html>", response.getBody(), "response body should contain rendered parameters");
		}

		@Test
		@DisplayName("generates HTML with parameters in the body")
		void generates_html_with_parameters_in_the_body() throws MalformedURLException, UnsupportedEncodingException {
			String body = ((DynamicAsset) dynamicAsset).retrieveBody(request);

			assertEquals("<!doctype html><html><head></head><body>param1:bachelard's poetics of space<br></body></html>", body, "retrieveBody should return full HTML with parameters");
		}
	}
}
