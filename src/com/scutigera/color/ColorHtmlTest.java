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
		assertEquals(htmlstring,"<!doctype html><html><head></head><body>Red</body></html>");
	}

}
