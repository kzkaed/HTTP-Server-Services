package server.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import server.response.ResponseCodes;

@DisplayName("ResponseCodes")
public class ResposeCodesTest {

	@Nested
	@DisplayName("when looking up reason phrases")
	class WhenLookingUpReasonPhrases {

		@Test
		@DisplayName("returns OK for status code 200")
		void returns_ok_for_status_code_200() {
			String reasonPhrase = ResponseCodes.getReason("200");
			assertEquals(reasonPhrase, "OK", "200 should map to OK");
		}

		@Test
		@DisplayName("returns Created for status code 201")
		void returns_created_for_status_code_201() {
			String reasonPhrase = ResponseCodes.getReason("201");
			assertEquals(reasonPhrase, "Created", "201 should map to Created");
		}

		@Test
		@DisplayName("returns Accepted for status code 202")
		void returns_accepted_for_status_code_202() {
			String reasonPhrase = ResponseCodes.getReason("202");
			assertEquals(reasonPhrase, "Accepted", "202 should map to Accepted");
		}

		@Test
		@DisplayName("returns Not Found for status code 404")
		void returns_not_found_for_status_code_404() {
			String reasonPhrase = ResponseCodes.getReason("404");
			assertEquals(reasonPhrase, "Not Found", "404 should map to Not Found");
		}

		@Test
		@DisplayName("returns Internal Server Error for status code 500")
		void returns_internal_server_error_for_status_code_500() {
			String reasonPhrase = ResponseCodes.getReason("500");
			assertEquals(reasonPhrase, "Internal Server Error", "500 should map to Internal Server Error");
		}

		@Test
		@DisplayName("returns Not Implemented for status code 502")
		void returns_not_implemented_for_status_code_502() {
			String reasonPhrase = ResponseCodes.getReason("502");
			assertEquals(reasonPhrase, "Not Implemented", "502 should map to Not Implemented");
		}
	}
}
