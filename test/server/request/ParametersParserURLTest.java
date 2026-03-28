package server.request;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("ParametersParserURL")
public class ParametersParserURLTest {

	@Nested
	@DisplayName("when parsing a URI with query parameters and fragment")
	class WhenParsingAUriWithQueryParametersAndFragment {

		@Test
		@DisplayName("extracts and decodes parameter key-value pairs")
		void extracts_and_decodes_parameter_key_value_pairs() throws MalformedURLException, UnsupportedEncodingException {
			ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin&id=1&this=that+A%26B%3DC#paragraph2", "localhost", 0);
			Map<String,String> pairs = paramsParser.getParameterPairs();
			assertEquals(pairs.get("name"),"kristin", "name parameter should be decoded");
			assertEquals(pairs.get("id"),"1", "id parameter should be extracted");
			assertEquals(pairs.get("this"),"that A&B=C", "encoded characters should be decoded");
		}

		@Test
		@DisplayName("returns the raw query string without the fragment")
		void returns_the_raw_query_string_without_the_fragment() throws MalformedURLException, UnsupportedEncodingException {
			ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin&id=1&this=that+A%26B%3DC#paragraph2", "localhost", 0);
			String query = paramsParser.getQuery();
			assertEquals(query,"name=kristin&id=1&this=that+A%26B%3DC");
		}

		@Test
		@DisplayName("returns the full filename including query string but not the fragment")
		void returns_the_full_filename_including_query_string_but_not_the_fragment() throws MalformedURLException, UnsupportedEncodingException {
			ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin&id=1&this=that+A%26B%3DC#paragraph2", "localhost", 0);
			assertEquals(paramsParser.getFilename(),"/test?name=kristin&id=1&this=that+A%26B%3DC");
		}

		@Test
		@DisplayName("returns the path without the query string or fragment")
		void returns_the_path_without_the_query_string_or_fragment() throws MalformedURLException, UnsupportedEncodingException {
			ParametersParser paramsParser = new ParametersParserURL("/test?name=kristin&id=1#paragraph2", "localhost", 0);
			assertEquals("/test",paramsParser.getPath());
		}
	}
}
