package com.scutigera.color;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import server.request.Request;
import server.response.Asset;

public class Color implements Asset {

	@Override
	public boolean canHandle(Request request) {
		 return request.getURI().startsWith("/color");			
	}

	@Override
	public String render(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		String color = request.getParmeters().get("color");
		if(color == null){
			String uri = request.getURI(); 
			color = uri.replace("/color/", "");
		}
		ColorHtml html = new ColorHtml(color);
		return html.build();
	}

	
}
