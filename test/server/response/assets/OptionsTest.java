package server.response.assets;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import server.request.Request;

@DisplayName("Options")
public class OptionsTest {
	private final String CRLF = server.constants.Constant.CRLF;
	private final String HEADERS_END = server.constants.Constant.HEADERS_END;
	private Options options;

	@BeforeEach
	public void setUp() throws Exception {
		options = new Options();
	}

	@Nested
	@DisplayName("when checking if it can handle a request")
	class WhenCheckingIfItCanHandleARequest {

		@Test
		@DisplayName("returns true for an OPTIONS request")
		void returns_true_for_an_options_request() {
			String requestLine = "OPTIONS / HTTP/1.1";
			Request request = new Request("OPTIONS","/","HTTP/1.1",null,null,requestLine,null);
			assertTrue(options.canHandle(request), "should handle OPTIONS requests");
		}
	}

	@Nested
	@DisplayName("when executing an OPTIONS request")
	class WhenExecutingAnOptionsRequest {

		@Test
		@DisplayName("returns a response listing all allowed methods")
		void returns_a_response_listing_all_allowed_methods() throws MalformedURLException, UnsupportedEncodingException {
			String requestLine = "OPTIONS / HTTP/1.1";
			Request request = new Request("OPTIONS","/","HTTP/1.1",null,null,requestLine,null);
			assertEquals("HTTP/1.1 200 OK" + CRLF
					+ "Allow: GET,HEAD,POST,OPTIONS,PUT" + HEADERS_END,
					options.execute(request).getResponseAsString(),
					"response should include Allow header with all methods");
		}
	}
}
