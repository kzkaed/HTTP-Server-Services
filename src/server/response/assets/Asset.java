package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import server.request.Request;
import server.response.Response;

public interface Asset {
	public boolean canHandle(Request request);
	public Response render(Request request) throws MalformedURLException, UnsupportedEncodingException;
}


