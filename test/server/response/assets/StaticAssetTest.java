package server.response.assets;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import server.request.Request;
import server.response.Response;
import server.response.assets.StaticAsset;

@DisplayName("StaticAsset")
public class StaticAssetTest {

	private StaticAsset asset;

	@BeforeEach
	public void setUp()  {
		asset = new StaticAsset("public");
	}

	@Nested
	@DisplayName("when checking if it can handle a request")
	class WhenCheckingIfItCanHandleARequest {

		@Test
		@DisplayName("returns true for a known static file")
		void returns_true_for_a_known_static_file() {
			Request request = new Request("GET","/file1","HTTP1/1", null, "GET /file1 HTTP1/1",null,new HashMap<>());
			assertTrue(asset.canHandle(request), "should handle requests for existing static files");
		}
	}

	@Nested
	@DisplayName("when serving file content")
	class WhenServingFileContent {

		@Test
		@DisplayName("returns the full file contents for a simple file")
		void returns_the_full_file_contents_for_a_simple_file() throws MalformedURLException, UnsupportedEncodingException {
			String content = "file1 contents";
			Request request = new Request("GET","/file1","HTTP1/1", null, "GET /file1 HTTP1/1",null,new HashMap<>());
			Response response = asset.execute(request);
			String contentReceived = response.getBody();
			assertEquals(content, contentReceived, "body should match file1 contents");
		}

		@Test
		@DisplayName("returns the full contents of a partial content file")
		void returns_the_full_contents_of_a_partial_content_file() throws MalformedURLException, UnsupportedEncodingException {
			String content = "This is a file that contains text to read part of in order to fulfill a 206.";
			Request request = new Request("GET","/partial_content.txt","HTTP1/1", null, "GET /partial_content.txt HTTP1/1",null,new HashMap<>());

			Response response = asset.execute(request);
			String contentReceived = response.getBody();
			assertEquals(content, contentReceived, "body should match partial_content.txt contents");
		}

		@Test
		@DisplayName("returns content for a routed path")
		void returns_content_for_a_routed_path() throws MalformedURLException, UnsupportedEncodingException {
			String content = "<!doctype html><html><head></head><body>Test Static</body></html>";
			Request request = new Request("GET","/test/static","HTTP1/1", null, "GET /test/static HTTP1/1",null,new HashMap<>());
			Response response = asset.execute(request);
			String contentReceived = response.getBody();

			assertEquals(content, contentReceived, "body should match the routed static file content");
		}

		@Test
		@DisplayName("returns empty body when the file does not exist")
		void returns_empty_body_when_the_file_does_not_exist() throws MalformedURLException, UnsupportedEncodingException {
			String content = "";
			Request request = new Request("GET","/jam","HTTP1/1", null, "GET /jam HTTP1/1",null,new HashMap<>());
			Response response = asset.execute(request);
			String contentReceived = response.getBody();
			assertFalse(server.helpers.FileLocator.fileExist("/jam", "public"), "file /jam should not exist");

			assertEquals(content, contentReceived, "body should be empty for a missing file");
		}
	}
}
