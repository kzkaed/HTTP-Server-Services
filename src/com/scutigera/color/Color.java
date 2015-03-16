package com.scutigera.color;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;

import server.request.Request;
import server.response.ContentType;
import server.response.Response;
import server.response.ResponseCodes;
import server.response.assets.Asset;

public class Color implements Asset {

	@Override
	public boolean canHandle(Request request) {
		 return request.getURI().startsWith("/color");			
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		String color = request.getParmeters().get("color");
		if(color == null){
			String uri = request.getURI(); 
			color = uri.replace("/color/", "");
		}
		ColorHtml html = new ColorHtml(color);
		return new Response(html.build(),html.build().getBytes(), determineHeaders(ContentType.TEXT_HTML), 200, ResponseCodes.getReason("200"));
	}

	public HashMap<String,String> determineHeaders(String type) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Server", "Kristin Server");
		headers.put("Content-Type", type);
		return headers;
	}
}
