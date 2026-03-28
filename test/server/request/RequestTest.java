package server.request;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import server.request.Request;

@DisplayName("Request")
public class RequestTest {

	@Nested
	@DisplayName("when constructed with all fields")
	class WhenConstructedWithAllFields {

		@Test
		@DisplayName("exposes all request tokens via getters")
		void exposes_all_request_tokens_via_getters() {
			String method = "";
			String uri = "";
			String protocolVersion = "";
			Map<String,String> headers = new HashMap<>();
			String requestLine = "GET / HTTP1.1";
			String requestBody = "";
			Map<String,String> parameters = new HashMap<>();

			Request request = new Request(method,uri,protocolVersion, headers,requestLine,requestBody,parameters);
			assertEquals(request.getRequestLine(), requestLine, "request line should match");
			assertEquals(request.getMethod(), method, "method should match");
			assertEquals(request.getProtocolVersion(), protocolVersion, "protocol version should match");
			assertEquals(request.getURI(), uri, "URI should match");
			assertNotNull(request.getParmeters(), "parameters should not be null");
			assertNotNull(request.getHeaders(), "headers should not be null");
		}
	}

	@Nested
	@DisplayName("when using setters")
	class WhenUsingSetters {

		@Test
		@DisplayName("sets and retrieves a parameter")
		void sets_and_retrieves_a_parameter() {
			Request request = new Request();
			request.setParameter("howya", "doin");
			assertEquals(request.getParmeters().get("howya"), "doin", "parameter value should be retrievable after setting");
		}

		@Test
		@DisplayName("sets and retrieves the URI")
		void sets_and_retrieves_the_uri() {
			Request request = new Request();
			request.setURI("test");
			assertEquals(request.getURI(), "test", "URI should be retrievable after setting");
		}

		@Test
		@DisplayName("sets and retrieves the method")
		void sets_and_retrieves_the_method() {
			Request request = new Request();
			request.setMethod("GET");
			assertEquals(request.getMethod(), "GET", "method should be retrievable after setting");
		}
	}
}
