package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;

import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseCodes;

public class Put implements Asset {
	
	public Put() {}

	@Override
	public boolean canHandle(Request request) {
		return request.getMethod().equals(Method.PUT);
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		HashMap<String,String> headers = determineHeaders("text/html");
		return new Response("","".getBytes("UTF8"), headers, 200, ResponseCodes.getReason("200"));
	}

	public HashMap<String,String> determineHeaders(String type) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Server", "Kristin Server");
		headers.put("Content-Type", type);
		return headers;
	}
}
