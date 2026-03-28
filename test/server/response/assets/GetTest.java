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

@DisplayName("Get")
public class GetTest {

	Asset get;
	@BeforeEach
	public void setUp() throws Exception {
		get = new Get();
	}

	@Nested
	@DisplayName("when checking if it can handle a request")
	class WhenCheckingIfItCanHandleARequest {

		@Test
		@DisplayName("returns true for a GET method")
		void returns_true_for_a_get_method() {
			Request request = new Request();
			request.setMethod("GET");
			assertTrue(get.canHandle(request), "should handle GET requests");
		}
	}

	@Nested
	@DisplayName("when executing a GET request")
	class WhenExecutingAGetRequest {

		@Test
		@DisplayName("returns a 200 OK response")
		void returns_a_200_ok_response() throws MalformedURLException, UnsupportedEncodingException {
			Request request = new Request();
			request.setMethod("GET");
			Response response = get.execute(request);
			assertNotNull(response, "response should not be null");
			assertEquals("HTTP/1.1 200 OK\r\n", response.getStatusLine(), "status line should be 200 OK");
		}
	}
}
