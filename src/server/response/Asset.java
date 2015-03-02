package server.response;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import server.request.Request;

public interface Asset {
	
	public String render(Request request) throws MalformedURLException, UnsupportedEncodingException;
}


