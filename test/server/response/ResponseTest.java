package server.response;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import server.constants.Constant;

@DisplayName("Response")
public class ResponseTest {

	Response response;
	String HEADERS_END = Constant.HEADERS_END;
	String CRLF = Constant.CRLF;
	String COLON = Constant.COLON;

	@BeforeEach
	public void setUp() throws Exception {

		HashMap<String,String> headers = new HashMap<String,String>();
		headers.put("Server", "Kristin Server");

		response = new Response("body","body".getBytes(),headers,200,ResponseCodes.getReason("200"));
	}

	@Nested
	@DisplayName("when accessing response parts")
	class WhenAccessingResponseParts {

		@Test
		@DisplayName("returns formatted headers with CRLF termination")
		void returns_formatted_headers_with_crlf_termination() {
			assertEquals("Server: Kristin Server" + HEADERS_END, response.getHeaders(), "headers should be formatted with colon separator and CRLF ending");
		}

		@Test
		@DisplayName("returns the body as a string")
		void returns_the_body_as_a_string() {
			assertEquals(response.getBody(), "body", "body should match the string passed at construction");
		}

		@Test
		@DisplayName("returns the body as a byte array")
		void returns_the_body_as_a_byte_array() {
			assertNotNull(response.getBodyBytes().getClass(), "body bytes should not be null");
		}
	}

	@Nested
	@DisplayName("when building the response")
	class WhenBuildingTheResponse {

		@Test
		@DisplayName("constructs the status line from code and message")
		void constructs_the_status_line_from_code_and_message() {
			response.setResponseStatusCode(200);
			response.setResponseStatusMessage("OK");

			assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK" + CRLF, "status line should follow HTTP/1.1 format");
		}

		@Test
		@DisplayName("includes headers in the response")
		void includes_headers_in_the_response() {
			response.setResponseStatusCode(200);
			response.setResponseStatusMessage("OK");

			assertEquals(response.getHeaders(), "Server: Kristin Server" + HEADERS_END, "headers should be present in the built response");
		}
	}
}
