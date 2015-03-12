package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import server.Methods;
import server.request.Request;
import server.response.Response;

public class Get implements Asset {
	
	public Get (){}

	@Override
	public boolean canHandle(Request request) {
		return request.getMethod().equals(Methods.GET);
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		return null;
	}

}
