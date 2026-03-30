package log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import log.mocks.StringLogger;

@DisplayName("SystemLogger")
public class SystemLoggerTest {

	@Nested
	@DisplayName("when logging messages")
	class WhenLoggingMessages {

		@Test
		@DisplayName("stores a log message")
		void stores_a_log_message() {
		    StringLogger log = new StringLogger();
		    log.log("Message");
		    assertEquals(log.logs.get(0), "Message", "log entry should match the provided message");
		}

		@Test
		@DisplayName("stores an error message")
		void stores_an_error_message() {
		    StringLogger log = new StringLogger();
		    log.error("Message");
		    assertEquals(log.errors.get(0), "Message", "error entry should match the provided message");
		}
	}

	@Nested
	@DisplayName("when accessing the singleton instance")
	class WhenAccessingSingletonInstance {

		@Test
		@DisplayName("returns a non-null instance")
		void returns_a_non_null_instance() {
			assertNotNull(SystemLogger.getInstance(), "getInstance should return a non-null logger");
		}

		@Test
		@DisplayName("returns the same instance on repeated calls")
		void returns_the_same_instance_on_repeated_calls() {
			SystemLogger first = SystemLogger.getInstance();
			SystemLogger second = SystemLogger.getInstance();
			assertSame(first, second, "getInstance should return the same instance each time");
		}
	}
}
