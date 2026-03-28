package server.response.assets;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import server.helpers.FileLocator;
import server.request.Request;
import server.response.Response;

@DisplayName("DirectoryAsset")
public class DirectoryAssetTest {

	private Asset directoryAsset;
	private Request request;

	@BeforeEach
	public void setUp() throws Exception {
		directoryAsset = new DirectoryAsset("public", new views.HtmlViewFactory());
		request = new Request("GET","/","HTTP1/1", null, "GET / HTTP1/1",null,new HashMap<>());
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Nested
	@DisplayName("when checking if it can handle a request")
	class WhenCheckingIfItCanHandleARequest {

		@Test
		@DisplayName("returns true for a directory listing request")
		void returns_true_for_a_directory_listing_request() {
			assertTrue(directoryAsset.canHandle(request), "should handle GET / as a directory request");
		}
	}

	@Nested
	@DisplayName("when executing a directory request")
	class WhenExecutingADirectoryRequest {

		@Test
		@DisplayName("lists all non-hidden files in the response body")
		void lists_all_non_hidden_files_in_the_response_body() throws MalformedURLException, UnsupportedEncodingException {
			String directory = server.helpers.FileLocator.getAbsolutePath("/public");
			File[] files = new File(directory).listFiles();
			List<String> results = new ArrayList<String>();
			for (File file : files) {
			    if (file.isFile() && !file.isHidden()) {
			        results.add(file.getName());
			    }
			}
			Response response = directoryAsset.execute(request);
			String contentReceived = response.getBody();

			assertNotNull(results);
			for(int i = 0; i < results.size(); i ++){
				assertTrue(contentReceived.contains(results.get(i)), "body should contain file: " + results.get(i));
			}
			for(String result : results){
				assertTrue(contentReceived.contains(result), "body should contain file: " + result);
			}
		}
	}
}
