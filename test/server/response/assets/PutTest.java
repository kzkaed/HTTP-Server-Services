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

@DisplayName("Put")
public class PutTest {
	private String CRLF = server.constants.Constant.CRLF;
	private Put put;
	private Request request;

	@BeforeEach
	public void setUp() throws Exception {
		put = new Put();
		request = new Request();
	}

	@Nested
	@DisplayName("when checking if it can handle a request")
	class WhenCheckingIfItCanHandleARequest {

		@Test
		@DisplayName("returns true for a PUT request")
		void returns_true_for_a_put_request() {
			request.setMethod("PUT");
			request.setURI("/");
			assertTrue(put.canHandle(request), "should handle PUT requests");
		}
	}

	@Nested
	@DisplayName("when executing a PUT request")
	class WhenExecutingAPutRequest {

		@Test
		@DisplayName("returns a 200 OK status line")
		void returns_a_200_ok_status_line() throws MalformedURLException, UnsupportedEncodingException {
			request.setMethod("PUT");
			request.setURI("/");
			Response response = put.execute(request);
			assertEquals("HTTP/1.1 200 OK" + CRLF, response.getStatusLine(), "PUT should return 200 OK");
		}
	}
}
