package log;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
