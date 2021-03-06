package com.scutigera.color;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.Request;
import server.response.Response;

import com.scutigera.color.Color;

public class ColorTest {
	
	@Test
	public void testCanHandle() throws Exception {
		Color color = new Color();
		Request request = new Request("GET","/color","HTTP1/1", 
				new Hashtable<String,String>(), "GET /color?color=red HTTP1/1",
				null,new Hashtable<String,String>());
		
		assertTrue(color.canHandle(request));
	}
	
	@Test
	public void testCanNotHandle() throws Exception {
		Color color = new Color();
		Request request = new Request("GET","/hello","HTTP1/1", 
				new Hashtable<String,String>(), "GET /hello HTTP1/1",null,
				new Hashtable<String,String>());
		
		assertFalse(color.canHandle(request));
	}

	@Test
	public void testGeneratesHtml() throws MalformedURLException, UnsupportedEncodingException {		
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
				+ "</body></html>",html);
	}
	
	@Test
	public void testGeneratesHtmlFromURI() throws Exception {
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
				+ "</body></html>",html);
	}
	
}
