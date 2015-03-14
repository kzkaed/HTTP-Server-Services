package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import server.Methods;
import server.request.Request;
import server.response.Response;

public class Get implements Asset {
	
	public Get (){}

	
	public boolean canHandle(Request request) {
		System.out.println("GET");
		return request.getMethod().equals(Methods.GET);
	}


	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		return null;
	}

}
