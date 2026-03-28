package server.response.assets;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import server.helpers.FileLocator;
import server.request.Request;
import server.response.Response;

@DisplayName("ImageAsset")
public class ImageAssetTest {

	private ImageAsset imgAsset;
	@BeforeEach
	public void setUp() throws Exception {
		imgAsset = new ImageAsset("public");
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Nested
	@DisplayName("when checking supported image types")
	class WhenCheckingSupportedImageTypes {

		@Test
		@DisplayName("can handle a JPEG image")
		void can_handle_a_jpeg_image() {
			Request request = new Request();
			request.setURI("/image.jpeg");
			boolean handle = imgAsset.canHandle(request);

			assertTrue(handle, "should handle .jpeg files");
		}

		@Test
		@DisplayName("can handle a GIF image")
		void can_handle_a_gif_image() {
			Request request = new Request();
			request.setURI("/image.gif");
			boolean handle = imgAsset.canHandle(request);

			assertTrue(handle, "should handle .gif files");
		}

		@Test
		@DisplayName("can handle a PNG image")
		void can_handle_a_png_image() {
			Request request = new Request();
			request.setURI("/image.png");
			boolean handle = imgAsset.canHandle(request);

			assertTrue(handle, "should handle .png files");
		}
	}

	@Nested
	@DisplayName("when resolving MIME types")
	class WhenResolvingMimeTypes {

		@Test
		@DisplayName("returns image/jpeg for a JPEG file")
		void returns_image_jpeg_for_a_jpeg_file() throws IOException {
			FileNameMap fileNameMap = URLConnection.getFileNameMap();
			String type = fileNameMap.getContentTypeFor("/image.jpeg");

			assertEquals(type, "image/jpeg", "MIME type for .jpeg should be image/jpeg");
		}
	}

	@Nested
	@DisplayName("when executing an image request")
	class WhenExecutingAnImageRequest {

		@Test
		@DisplayName("returns the image file as bytes in the response")
		void returns_the_image_file_as_bytes_in_the_response() throws IOException {
			Request request = new Request();
			request.setURI("/image.jpeg");

			Path path = Paths.get(FileLocator.webrootAbsolutePath("public") + request.getURI());

			byte[] imageInBytes = Files.readAllBytes(path);

			assertNotNull(imageInBytes, "image bytes should not be null");

			Response response = imgAsset.execute(request);
			assertArrayEquals(imageInBytes, response.getBodyBytes(), "response body bytes should match the file contents");
			assertEquals("image: " + request.getURI(), response.getBody(), "response body string should indicate the image URI");
		}
	}
}
