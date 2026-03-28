package server.request;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("ParametersParserRegex")
public class ParametersParserRegexTest {

	@Nested
	@DisplayName("when parsing a URI with query parameters")
	class WhenParsingAUriWithQueryParameters {

		@Test
		@DisplayName("extracts and decodes parameter key-value pairs")
		void extracts_and_decodes_parameter_key_value_pairs() throws MalformedURLException, UnsupportedEncodingException {
			ParametersParser paramsParser = new ParametersParserRegex("/test?name=kristin&id=1&this=that+A%26B%3DC");
			Map<String,String> pairs = paramsParser.getParameterPairs();
			assertEquals(pairs.get("name"),"kristin", "name parameter should be decoded");
			assertEquals(pairs.get("id"),"1", "id parameter should be extracted");
			assertEquals(pairs.get("this"),"that A&B=C", "encoded characters should be decoded");
		}

		@Test
		@DisplayName("returns the raw query string")
		void returns_the_raw_query_string() throws MalformedURLException, UnsupportedEncodingException {
			ParametersParser paramsParser = new ParametersParserRegex("/test?name=kristin&id=1&this=that+A%26B%3DC");
			String query = paramsParser.getQuery();
			assertEquals(query,"name=kristin&id=1&this=that+A%26B%3DC");
		}

		@Test
		@DisplayName("returns the full filename including query string")
		void returns_the_full_filename_including_query_string() throws MalformedURLException, UnsupportedEncodingException {
			ParametersParser paramsParser = new ParametersParserRegex("/test?name=kristin&id=1&this=that+A%26B%3DC");
			assertEquals(paramsParser.getFilename(),"/test?name=kristin&id=1&this=that+A%26B%3DC");
		}

		@Test
		@DisplayName("returns the path without the query string")
		void returns_the_path_without_the_query_string() throws MalformedURLException, UnsupportedEncodingException {
			ParametersParser paramsParser = new ParametersParserRegex("/test?name=kristin&id=1");
			assertEquals("/test",paramsParser.getPath());
		}
	}
}
