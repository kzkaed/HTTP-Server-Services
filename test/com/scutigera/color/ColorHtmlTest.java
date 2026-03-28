package com.scutigera.color;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.scutigera.color.ColorHtml;

@DisplayName("ColorHtml")
public class ColorHtmlTest {

	@Nested
	@DisplayName("when building HTML")
	class WhenBuildingHtml {

		@Test
		@DisplayName("produces a complete HTML page with the given background color and color links")
		void produces_a_complete_html_page_with_background_color_and_links() {
			ColorHtml html = new ColorHtml("Red");
			String htmlstring = html.build();
			assertEquals(htmlstring,"<!doctype html>"
					+ "<html><head></head>"
					+ "<body bgcolor=\"Red\">"
					+ "<a href=\"/color/aqua\">aqua</a><br>"
					+ "<a href=\"/color/green\">green</a><br>"
					+ "<a href=\"/color/red\">red</a><br>"
					+ "<a href=\"/color/yellow\">yellow</a><br>"
					+ "<a href=\"/color/black\">black</a><br>"
					+ "<a href=\"/color/gray\">gray</a><br>"
					+ "<a href=\"/color/white\">white</a><br>"
					+ "</body></html>",
					"HTML output should contain background color and all color links");
		}
	}
}
