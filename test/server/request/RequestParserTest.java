package server.request;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import server.constants.Constant;
import server.request.RequestParser;

@DisplayName("RequestParser")
public class RequestParserTest {
	String HEADERS_END = Constant.HEADERS_END;
	String CRLF = Constant.CRLF;
	String COLON = Constant.COLON;

	@Nested
	@DisplayName("when parsing the request line")
	class WhenParsingTheRequestLine {

		@Test
		@DisplayName("extracts method, URI, and protocol from a simple request")
		void extracts_method_uri_and_protocol_from_a_simple_request() throws IOException {
			String request = "GET /test/index HTTP1/1";
			ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
			BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
			RequestParser parser = new RequestParser(in, "localhost", 0);
			Request resultRequest = parser.parseRequest();

			Request expectRequest = new Request("GET","/test/index","HTTP1/1", null, "GET /test/index HTTP1/1",null,null);

			assertEquals(resultRequest.getMethod(),expectRequest.getMethod(), "method should match");
			assertEquals(resultRequest.getProtocolVersion(),expectRequest.getProtocolVersion(), "protocol version should match");
			assertEquals(resultRequest.getRequestLine(),expectRequest.getRequestLine(), "request line should match");
			assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
			assertEquals(resultRequest.getURI(),expectRequest.getURI(), "URI should match");
			assertEquals(resultRequest.getClass(),resultRequest.getClass());
		}

		@Test
		@DisplayName("parses a request with a query string and fragment")
		void parses_a_request_with_a_query_string_and_fragment() throws IOException {
			String request = "GET /test/index?id=1&test=true#REF HTTP1/1";
			ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
			BufferedReader in = new BufferedReader(new InputStreamReader(inStream));

			Request expectRequest = new Request("GET","/test/index","HTTP1/1", null, "GET /test/index?id=1&test=true#REF HTTP1/1",null,null);
			RequestParser parser = new RequestParser(in, "localhost", 0);
			Request resultRequest = parser.parseRequest();
			assertEquals(resultRequest.getMethod(),expectRequest.getMethod(), "method should match");
			assertEquals(resultRequest.getProtocolVersion(),expectRequest.getProtocolVersion(), "protocol version should match");
			assertEquals(resultRequest.getRequestLine(),expectRequest.getRequestLine(), "request line should match");
			assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
			assertEquals(resultRequest.getURI(),expectRequest.getURI(), "URI should match");
			assertEquals(resultRequest.getClass(),resultRequest.getClass());
		}

		@Test
		@DisplayName("returns empty parameters when there is no query string")
		void returns_empty_parameters_when_there_is_no_query_string() throws IOException {
			String request = "GET /test/index HTTP1/1";
			ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
			BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
			RequestParser parser = new RequestParser(in, "localhost", 0);
			Request resultRequest = parser.parseRequest();

			assertNotNull(resultRequest.getParmeters(), "parameters should not be null");
			assertTrue(resultRequest.getParmeters().isEmpty(), "parameters should be empty when no query string is present");
		}
	}

	@Nested
	@DisplayName("when parsing URL-encoded parameters")
	class WhenParsingUrlEncodedParameters {

		@Test
		@DisplayName("decodes a URL-encoded string")
		void decodes_a_url_encoded_string() throws UnsupportedEncodingException {
			String url = "https%3A%2F%2Fmywebsite%2Fdocs%2Fenglish%2Fsite%2Fmybook.do" +
		               "%3Frequest_type%3D%26type%3Dprivate";
			String decoded = java.net.URLDecoder.decode(url, server.constants.Constant.UTF_8);
			assertEquals("https://mywebsite/docs/english/site/mybook.do?request_type=&type=private", decoded);
		}

		@Test
		@DisplayName("decodes percent-encoded spaces in parameter values")
		void decodes_percent_encoded_spaces_in_parameter_values() throws IOException {
			String request = "GET /test/index?name=kristin%20kaeding HTTP1/1";
			ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
			BufferedReader in = new BufferedReader(new InputStreamReader(inStream));

			Request expectRequest = new Request("GET","/test/index","HTTP1/1", null, "GET /test/index?name=kristin%20kaeding HTTP1/1",null,null);
			RequestParser parser = new RequestParser(in, "localhost", 0);
			Request resultRequest = parser.parseRequest();
			assertEquals(resultRequest.getMethod(),expectRequest.getMethod(), "method should match");
			assertEquals(resultRequest.getProtocolVersion(),expectRequest.getProtocolVersion(), "protocol version should match");
			assertEquals(resultRequest.getRequestLine(),expectRequest.getRequestLine(), "request line should match");
			assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
			assertEquals(resultRequest.getURI(),expectRequest.getURI(), "URI should match");
			assertEquals(resultRequest.getClass(),resultRequest.getClass());
			assertEquals(resultRequest.getParmeters().get("name").toString(), "kristin kaeding", "decoded name parameter should contain a space");
		}

		@Test
		@DisplayName("decodes obscure percent-encoded characters in parameter values")
		void decodes_obscure_percent_encoded_characters_in_parameter_values() throws IOException {
			String request = "GET /test/index?name=kristin%20kaeding&this=that%20A%2BB%3DC HTTP1/1";
			ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
			BufferedReader in = new BufferedReader(new InputStreamReader(inStream));

			Request expectRequest = new Request("GET","/test/index","HTTP1/1", null, "GET /test/index?name=kristin%20kaeding&this=that%20A%2BB%3DC HTTP1/1",null,null);
			RequestParser parser = new RequestParser(in, "localhost", 0);
			Request resultRequest = parser.parseRequest();
			assertEquals(resultRequest.getMethod(),expectRequest.getMethod(), "method should match");
			assertEquals(resultRequest.getProtocolVersion(),expectRequest.getProtocolVersion(), "protocol version should match");
			assertEquals(resultRequest.getRequestLine(),expectRequest.getRequestLine(), "request line should match");
			assertEquals(resultRequest.getMethod(),expectRequest.getMethod());
			assertEquals(resultRequest.getURI(),expectRequest.getURI(), "URI should match");
			assertEquals(resultRequest.getClass(),resultRequest.getClass());
			assertEquals(resultRequest.getParmeters().get("name").toString(), "kristin kaeding", "decoded name parameter should contain a space");
			assertEquals(resultRequest.getParmeters().get("this").toString(), "that A+B=C", "decoded 'this' parameter should contain special characters");
		}
	}

	@Nested
	@DisplayName("when reading headers and body")
	class WhenReadingHeadersAndBody {

		@Test
		@DisplayName("splits the remaining request into headers and body")
		void splits_the_remaining_request_into_headers_and_body() throws IOException {
			String restOfRequest = "Host: scutigera.com" + CRLF
					+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:36.0) Gecko/20100101 Firefox/36.0"+ CRLF
					+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"+ CRLF
					+ "Accept-Language: en-US,en;q=0.5"+ CRLF
					+ "Accept-Encoding: gzip, deflate"+ CRLF
					+ "Connection: keep-alive"+ HEADERS_END
					+ "body";

			ByteArrayInputStream inStream = new ByteArrayInputStream(restOfRequest.getBytes());
			BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
			RequestParser parser = new RequestParser(in, "localhost", 0);

			String[] resultHeaders = parser.readRemainingRequest();
			String headers = "Host: scutigera.com" + CRLF
					+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:36.0) Gecko/20100101 Firefox/36.0"+ CRLF
					+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"+ CRLF
					+ "Accept-Language: en-US,en;q=0.5"+ CRLF
					+ "Accept-Encoding: gzip, deflate"+ CRLF
					+ "Connection: keep-alive";
			String body = "body";

			assertEquals(resultHeaders[0], headers, "headers section should be separated from body");
			assertEquals(resultHeaders[1], body, "body section should be separated from headers");
		}

		@Test
		@DisplayName("parses header lines into key-value pairs")
		void parses_header_lines_into_key_value_pairs() {
			String request = "GET / HTTP1/1";
			String headers = "Host: scutigera.com" + CRLF
					+ "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:36.0) Gecko/20100101 Firefox/36.0"+ CRLF
					+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"+ CRLF
					+ "Accept-Language: en-US,en;q=0.5"+ CRLF
					+ "Accept-Encoding: gzip, deflate"+ CRLF
					+ "Connection: keep-alive";

			ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
			BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
			RequestParser parser = new RequestParser(in, "localhost", 0);
			Map<String,String> headersPairs = parser.parseHeaders(headers);
			assertEquals(headersPairs.get("User-Agent"),"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:36.0) Gecko/20100101 Firefox/36.0", "User-Agent header should be parsed");
			assertEquals(headersPairs.get("Host"),"scutigera.com", "Host header should be parsed");
			assertEquals(headersPairs.get("Accept"),"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8", "Accept header should be parsed");
			assertEquals(headersPairs.get("Connection"),"keep-alive", "Connection header should be parsed");
			assertEquals(headersPairs.get("Accept-Encoding"),"gzip, deflate", "Accept-Encoding header should be parsed");
		}
	}
}
