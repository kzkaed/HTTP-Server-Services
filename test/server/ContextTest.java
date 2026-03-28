package server;

import static org.junit.jupiter.api.Assertions.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Context")
public class ContextTest {

	@Nested
	@DisplayName("when configuring the server context")
	class WhenConfiguringTheServerContext {

		@Test
		@DisplayName("sets the port")
		void sets_the_port() {
			Context.setPort(5000);
			assertEquals(5000, Context.PORT_IN_USE, "port should be updated to the provided value");
		}

		@Test
		@DisplayName("resolves the host name")
		void resolves_the_host_name() {
			String host = "not able to be determined";
			try {
				host = InetAddress.getLocalHost().getHostName().toString();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			assertEquals(host, Context.HOST, "host should match the local hostname");
		}
	}
}
