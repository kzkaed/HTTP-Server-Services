package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;

import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseCodes;

public class Get implements Asset {

	public boolean canHandle(Request request) {
		return request.getMethod().equals(Method.GET);
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		HashMap<String,String> headers = new HashMap<String,String>();
		headers.put("Server", "Kristin Server");
		headers.put("Content-Type", "text/html");
		return new Response("", "".getBytes("UTF8"), headers, 200, ResponseCodes.getReason("200"));
	}

}
