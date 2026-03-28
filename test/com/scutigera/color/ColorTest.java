package com.scutigera.color;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import server.request.Request;
import server.response.Response;

import com.scutigera.color.Color;

@DisplayName("Color")
public class ColorTest {

	@Nested
	@DisplayName("when checking if it can handle a request")
	class WhenCheckingCanHandle {

		@Test
		@DisplayName("returns true for a /color request")
		void returns_true_for_a_color_request() throws Exception {
			Color color = new Color();
			Request request = new Request("GET","/color","HTTP1/1",
					new HashMap<>(), "GET /color?color=red HTTP1/1",
					null,new HashMap<>());

			assertTrue(color.canHandle(request), "should handle requests to /color");
		}

		@Test
		@DisplayName("returns false for a non-color request")
		void returns_false_for_a_non_color_request() throws Exception {
			Color color = new Color();
			Request request = new Request("GET","/hello","HTTP1/1",
					new HashMap<>(), "GET /hello HTTP1/1",null,
					new HashMap<>());

			assertFalse(color.canHandle(request), "should not handle requests to /hello");
		}
	}

	@Nested
	@DisplayName("when executing a request")
	class WhenExecuting {

		@Test
		@DisplayName("generates HTML from a color parameter")
		void generates_html_from_a_color_parameter() throws MalformedURLException, UnsupportedEncodingException {
			Color color = new Color();
			Request request = new Request();
			request.setParameter("color", "Blue");

			Response response = color.execute(request);
			String html = response.getBody();
			assertEquals("<!doctype html>"
					+ "<html><head></head>"
					+ "<body bgcolor=\"Blue\">"
					+ "<a href=\"/color/aqua\">aqua</a><br>"
					+ "<a href=\"/color/green\">green</a><br>"
					+ "<a href=\"/color/red\">red</a><br>"
					+ "<a href=\"/color/yellow\">yellow</a><br>"
					+ "<a href=\"/color/black\">black</a><br>"
					+ "<a href=\"/color/gray\">gray</a><br>"
					+ "<a href=\"/color/white\">white</a><br>"
					+ "</body></html>",html,
					"HTML body should use the color parameter as the background color");
		}

		@Test
		@DisplayName("generates HTML from the URI path")
		void generates_html_from_the_uri_path() throws Exception {
			Color color = new Color();
			Request request = new Request();
			request.setURI("/color/Blue");
			Response response = color.execute(request);
			String html = response.getBody();
			assertEquals("<!doctype html>"
					+ "<html><head></head>"
					+ "<body bgcolor=\"Blue\">"
					+ "<a href=\"/color/aqua\">aqua</a><br>"
					+ "<a href=\"/color/green\">green</a><br>"
					+ "<a href=\"/color/red\">red</a><br>"
					+ "<a href=\"/color/yellow\">yellow</a><br>"
					+ "<a href=\"/color/black\">black</a><br>"
					+ "<a href=\"/color/gray\">gray</a><br>"
					+ "<a href=\"/color/white\">white</a><br>"
					+ "</body></html>",html,
					"HTML body should extract the color from the URI path");
		}
	}
}
