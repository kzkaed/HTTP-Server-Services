package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Hashtable;

import server.request.Request;
import server.response.ContentType;
import server.response.Response;
import server.response.ResponseCodes;
import views.HtmlView;

public class Parameter implements Asset {
	
	private Hashtable<String,String> parameters;
	private HtmlView html;
	
	public Parameter () {}

	@Override
	public boolean canHandle(Request request) {
		return request.getURI().contentEquals("/parameters");
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		if (request.getParmeters() != null && !request.getParmeters().isEmpty()){
			parameters = request.getParmeters();
			html = new HtmlView(parameters);
		}
						
		return new Response(html.build(),html.build().getBytes() ,determineHeaders(ContentType.TEXT_HTML), 200, ResponseCodes.getReason("200"));
	}
	
	public HashMap<String,String> determineHeaders(String type) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Server", "Kristin Server");
		headers.put("Content-Type", type);
		return headers;
	}

}
