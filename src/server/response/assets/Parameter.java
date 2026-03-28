package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;

import server.request.Request;
import server.response.ContentType;
import server.response.Response;
import server.response.ResponseCodes;
import views.ViewFactory;

public class Parameter implements Asset {

	private final ViewFactory viewFactory;

	public Parameter(ViewFactory viewFactory) {
		this.viewFactory = viewFactory;
	}

	@Override
	public boolean canHandle(Request request) {
		return request.getURI().contentEquals("/parameters");
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		String body = viewFactory.forParameters(request.getParmeters()).build();
		return new Response(body, body.getBytes(), determineHeaders(ContentType.TEXT_HTML), 200, ResponseCodes.getReason("200"));
	}
	
	public HashMap<String,String> determineHeaders(String type) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Server", "Kristin Server");
		headers.put("Content-Type", type);
		return headers;
	}

}
