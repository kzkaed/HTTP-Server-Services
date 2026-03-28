package server.helpers;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("FileLocator")
public class FileLocatorTest {

	@Nested
	@DisplayName("when checking if a file exists")
	class WhenCheckingIfAFileExists {

		@Test
		@DisplayName("returns false for a non-existent file")
		void returns_false_for_a_non_existent_file() {
			assertFalse(FileLocator.fileExist("/jam", "public"), "non-existent file should not be found");
		}

		@Test
		@DisplayName("returns true for an existing file")
		void returns_true_for_an_existing_file() {
			assertTrue(FileLocator.fileExist("/file1", "public"), "existing file should be found");
		}

		@Test
		@DisplayName("returns false for the root path")
		void returns_false_for_the_root_path() {
			assertFalse(FileLocator.fileExist("/", "public"), "root path should not match a file");
		}
	}

	@Nested
	@DisplayName("when resolving paths")
	class WhenResolvingPaths {

		@Test
		@DisplayName("returns the server path as a URI")
		void returns_the_server_path_as_a_uri() {
			Path path = Paths.get("");
			URI uri = path.toUri();
			assertEquals(FileLocator.findServerPathURI(), uri, "server path URI should match");
		}

		@Test
		@DisplayName("returns the server absolute path as a string")
		void returns_the_server_absolute_path_as_a_string() {
			Path path = Paths.get("");
			assertEquals(FileLocator.findServerAbsolutePath(), path.toAbsolutePath().toString(), "server absolute path should match");
		}

		@Test
		@DisplayName("returns the webroot absolute path with the directory appended")
		void returns_the_webroot_absolute_path_with_the_directory_appended() {
			Path path = Paths.get("");
			String expected = path.toAbsolutePath() + "/public";
			assertEquals(FileLocator.webrootAbsolutePath("public"), expected, "webroot absolute path should include the public directory");
		}
	}
}
