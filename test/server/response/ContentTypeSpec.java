package server.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ContentType")
public class ContentTypeSpec {

	@Nested
	@DisplayName("when resolving MIME type for a file path")
	class WhenResolvingMimeType {

		@Test
		@DisplayName("returns text/html for .html files")
		void returns_text_html_for_html() {
			assertEquals("text/html", ContentType.forPath("/index.html"));
		}

		@Test
		@DisplayName("returns text/html for .htm files")
		void returns_text_html_for_htm() {
			assertEquals("text/html", ContentType.forPath("/page.htm"));
		}

		@Test
		@DisplayName("returns text/css for .css files")
		void returns_text_css_for_css() {
			assertEquals("text/css", ContentType.forPath("/styles/main.css"));
		}

		@Test
		@DisplayName("returns application/javascript for .js files")
		void returns_application_javascript_for_js() {
			assertEquals("application/javascript", ContentType.forPath("/app.js"));
		}

		@Test
		@DisplayName("returns application/json for .json files")
		void returns_application_json_for_json() {
			assertEquals("application/json", ContentType.forPath("/data.json"));
		}

		@Test
		@DisplayName("returns text/plain for .txt files")
		void returns_text_plain_for_txt() {
			assertEquals("text/plain", ContentType.forPath("/readme.txt"));
		}

		@Test
		@DisplayName("returns image/jpeg for .jpeg files")
		void returns_image_jpeg_for_jpeg() {
			assertEquals("image/jpeg", ContentType.forPath("/photo.jpeg"));
		}

		@Test
		@DisplayName("returns image/png for .png files")
		void returns_image_png_for_png() {
			assertEquals("image/png", ContentType.forPath("/icon.png"));
		}

		@Test
		@DisplayName("returns image/gif for .gif files")
		void returns_image_gif_for_gif() {
			assertEquals("image/gif", ContentType.forPath("/animation.gif"));
		}
	}

	@Nested
	@DisplayName("when the file type is unknown")
	class WhenFileTypeIsUnknown {

		@Test
		@DisplayName("returns application/octet-stream for unrecognized extensions")
		void returns_octet_stream_for_unknown() {
			assertEquals("application/octet-stream", ContentType.forPath("/data.xyz"));
		}

		@Test
		@DisplayName("returns application/octet-stream for null path")
		void returns_octet_stream_for_null() {
			assertEquals("application/octet-stream", ContentType.forPath(null));
		}

		@Test
		@DisplayName("returns application/octet-stream for files with no extension")
		void returns_octet_stream_for_no_extension() {
			assertEquals("application/octet-stream", ContentType.forPath("/file1"));
		}
	}
}
