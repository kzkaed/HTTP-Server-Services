package com.scutigera.color;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import server.request.Request;
import server.response.Response;
import server.response.assets.Asset;

public class Color implements Asset {

	@Override
	public boolean canHandle(Request request) {
		 return request.getURI().startsWith("/color");			
	}

	@Override
	public Response render(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		String color = request.getParmeters().get("color");
		if(color == null){
			String uri = request.getURI(); 
			color = uri.replace("/color/", "");
		}
		ColorHtml html = new ColorHtml(color);
		//html.build();
		//Response (String responseBody, byte[] body, HashMap<String,String> headers){
		Response response = new Response(html.build(),html.build().getBytes(), null);
				
		return response;
	}

	
}
