package server;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("ArgsParser")
public class ArgsParserTest {

		@Nested
		@DisplayName("when using defaults")
		class WhenUsingDefaults {

			@Test
			@DisplayName("has default values matching the application constants")
			void has_default_values_matching_the_application_constants() {
				assertEquals(ArgsParser.PORT,server.constants.Constant.PORT_DEFAULT,
						"default port should match the application constant");
				assertEquals(ArgsParser.PUBLIC_DIR,server.constants.Constant.PUBLIC_DIR_DEFAULT,
						"default public directory should match the application constant");
			}

			@Test
			@DisplayName("populates context with defaults when no arguments are provided")
			void populates_context_with_defaults_when_no_arguments_are_provided() {
				ArgsParser parser = new ArgsParser();
				Map<String, String> context = parser.parse(new String[0]);
				assertEquals(context.get("Port"), ArgsParser.PORT, "port should default when no args given");
				assertEquals(context.get("Public Directory"), ArgsParser.PUBLIC_DIR,
						"public directory should default when no args given");
			}
		}

		@Nested
		@DisplayName("when arguments are provided")
		class WhenArgumentsAreProvided {

			@Test
			@DisplayName("parses the port from the first argument")
			void parses_the_port_from_the_first_argument() {
				String[] args = {"5000"};
				ArgsParser parser = new ArgsParser();
				Map<String, String> context = parser.parse(args);
				assertEquals(context.get("Port"), "5000", "port should be set from the first argument");
			}

			@Test
			@DisplayName("parses the public directory from the second argument")
			void parses_the_public_directory_from_the_second_argument() {
				String directory = "directory";
				String[] args = {"5000", directory};
				ArgsParser parser = new ArgsParser();
				assertEquals(parser.parse(args).get("Public Directory"), directory,
						"public directory should be set from the second argument");
			}
		}
}
