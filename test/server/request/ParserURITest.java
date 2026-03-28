package server.request;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("ParserURI")
public class ParserURITest {

	@Nested
	@DisplayName("when parsing query strings")
	class WhenParsingQueryStrings {

		@Test
		@DisplayName("extracts the raw query string from the URI")
		void extracts_the_raw_query_string_from_the_uri() throws URISyntaxException {
			String requestUri = "/test/index?name=kristin&id=1";
			ParametersParser parser = new ParserURI(requestUri);

			String expectedQuery = "name=kristin&id=1";
			assertEquals(expectedQuery, parser.getQuery(), "query string should be extracted from the URI");
		}

		@Test
		@DisplayName("decodes percent-encoded characters in the query string")
		void decodes_percent_encoded_characters_in_the_query_string() throws URISyntaxException {
			String requestUri = "/test/index?name=kristin%20kaeding&id=1";
			ParametersParser parser = new ParserURI(requestUri);

			String expectedQuery = "name=kristin kaeding&id=1";
			assertEquals(expectedQuery,parser.getQuery(), "percent-encoded spaces should be decoded");
		}
	}

	@Nested
	@DisplayName("when parsing parameters")
	class WhenParsingParameters {

		@Test
		@DisplayName("returns parameters as a query string")
		void returns_parameters_as_a_query_string() throws URISyntaxException {
			String requestUri = "/test/index?name=kristin&id=1";
			ParametersParser parser = new ParserURI(requestUri);

			String expectedParameter = "name=kristin&id=1";
			assertEquals(expectedParameter,parser.getQuery());
		}

		@Test
		@DisplayName("decodes percent-encoded values into parameter pairs")
		void decodes_percent_encoded_values_into_parameter_pairs() throws URISyntaxException, UnsupportedEncodingException {
			String requestUri = "/test/index?name=kristin%20kaeding&id=1&language=test";

			ParametersParser parser = new ParserURI(requestUri);

			String expectedParameter = "kristin kaeding";
			String expectedParameter2 = "1";
			String expectedParameter3 = "test";
			assertEquals(expectedParameter,parser.getParameterPairs().get("name"), "name should be decoded");
			assertEquals(expectedParameter2,parser.getParameterPairs().get("id"), "id should be extracted");
			assertEquals(expectedParameter3,parser.getParameterPairs().get("language"), "language should be extracted");
		}
	}
}
