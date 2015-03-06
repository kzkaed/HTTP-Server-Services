package com.scutigera.color;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.scutigera.color.ColorHtml;

public class ColorHtmlTest {



	@Test
	public void testHTMLbuilds() {
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
				+ "</body></html>");
	}
	
	


}
