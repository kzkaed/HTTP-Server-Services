package server.constants;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import server.constants.Method;

@DisplayName("Method")
public class MethodTest {

	@Nested
	@DisplayName("when accessing HTTP method constants")
	class WhenAccessingHttpMethodConstants {

		@Test
		@DisplayName("GET equals the string 'GET'")
		void get_equals_the_string_get() {
			assertEquals(Method.GET,"GET");
		}

		@Test
		@DisplayName("POST equals the string 'POST'")
		void post_equals_the_string_post() {
			assertEquals(Method.POST,"POST");
		}

		@Test
		@DisplayName("PUT equals the string 'PUT'")
		void put_equals_the_string_put() {
			assertEquals(Method.PUT,"PUT");
		}

		@Test
		@DisplayName("OPTIONS equals the string 'OPTIONS'")
		void options_equals_the_string_options() {
			assertEquals(Method.OPTIONS,"OPTIONS");
		}

		@Test
		@DisplayName("HEAD equals the string 'HEAD'")
		void head_equals_the_string_head() {
			assertEquals(Method.HEAD,"HEAD");
		}
	}

	@Nested
	@DisplayName("when checking if a method is implemented")
	class WhenCheckingIfAMethodIsImplemented {

		@Test
		@DisplayName("returns false for an unrecognized method")
		void returns_false_for_an_unrecognized_method() throws IOException {
			assertFalse(Method.isMethodImplemented("XYZ"), "unknown method should not be implemented");
		}

		@Test
		@DisplayName("returns true for a recognized method")
		void returns_true_for_a_recognized_method() throws IOException {
			assertTrue(Method.isMethodImplemented("GET"), "GET should be implemented");
		}
	}
}
