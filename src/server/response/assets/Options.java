package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;

import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseCodes;

public class Options implements Asset {
	
	public Options(){}
	
	@Override
	public boolean canHandle(Request request) {
		return request.getMethod().contentEquals(Method.OPTIONS) && request.getURI().contentEquals("/");
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		
		HashMap<String,String> headers = new HashMap<String,String>();
		headers.put("Allow", "GET,HEAD,POST,OPTIONS,PUT");
		return new Response("","".getBytes(),headers, 200, ResponseCodes.getReason("200"));
	}

}
