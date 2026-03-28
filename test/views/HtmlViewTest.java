package views;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("HtmlView")
public class HtmlViewTest {

	@Nested
	@DisplayName("when building an HTML page")
	class WhenBuildingAnHtmlPage {

		@Test
		@DisplayName("produces a minimal empty HTML document")
		void produces_a_minimal_empty_html_document() {
			assertEquals(new HtmlView().build(), "<!doctype html><html><head></head><body></body></html>", "should build a valid empty HTML page");
		}
	}
}
