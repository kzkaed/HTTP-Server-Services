package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import server.constants.Method;
import server.request.Request;
import server.response.Response;

public class Get implements Asset {
	
	
	public boolean canHandle(Request request) {
		return request.getMethod().equals(Method.GET);
	}


	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		
		return null;
	}

}
