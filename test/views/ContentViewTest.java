package views;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("ContentView")
public class ContentViewTest {

	@Nested
	@DisplayName("when rendering content")
	class WhenRenderingContent {

		@Test
		@DisplayName("wraps the given text in an HTML document")
		void wraps_the_given_text_in_an_html_document() {
			assertEquals("<!doctype html><html><head></head><body>hello</body></html>", new ContentView("hello").build(), "should wrap content in HTML boilerplate");
		}

		@Test
		@DisplayName("renders an empty body when content is blank")
		void renders_an_empty_body_when_content_is_blank() {
			assertEquals("<!doctype html><html><head></head><body></body></html>", new ContentView("").build(), "should produce valid HTML with empty body");
		}
	}
}
