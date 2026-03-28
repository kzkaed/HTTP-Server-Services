package routes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import views.HtmlView;

@DisplayName("HtmlView")
public class HtmlViewTest {

	@Nested
	@DisplayName("when building HTML")
	class WhenBuildingHtml {

		@Test
		@DisplayName("produces a minimal empty HTML document")
		void produces_a_minimal_empty_html_document() {
			assertEquals("<!doctype html><html><head></head><body></body></html>", new HtmlView().build(),
					"default build should produce an empty HTML shell");
		}
	}
}
