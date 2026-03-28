package server.response;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("ResponseSender")
public class ResponseSenderTest {
	private Response response;
	private OutputStream out;

	@BeforeEach
	public void setUp() throws Exception {
		out = new ByteArrayOutputStream();
	}

	@Nested
	@DisplayName("when sending a response")
	class WhenSendingAResponse {

		@Test
		@DisplayName("writes the full response to the output stream")
		void writes_the_full_response_to_the_output_stream() throws IOException {
			Response response = new Response("test", "test".getBytes(), new HashMap<String,String>(), 200, ResponseCodes.getReason("200"));
			ResponseSender sender = new ResponseSender(response, out);

			sender.send();

			assertEquals(response.getResponseAsString(), out.toString(), "output stream should contain the full response string");
		}
	}
}
