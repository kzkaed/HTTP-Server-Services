package server.response.assets;


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Hashtable;

import server.request.Request;
import server.response.Response;
import server.response.ResponseCodes;
import views.ViewFactory;

public class DynamicAsset implements Asset{

	protected Hashtable<String,String> parameters;
	protected String body;
	private final ViewFactory viewFactory;

	public DynamicAsset(ViewFactory viewFactory) {
		this.viewFactory = viewFactory;
	}
	
	@Override
	public boolean canHandle(Request request) {
			return request.getURI().contains("showParams"); 
		
	}
	
	public Response execute(Request request) throws MalformedURLException, UnsupportedEncodingException{
		body = retrieveBody(request);
		return new Response(body, body.getBytes(), retrieveHeaders("text/html"), 200, ResponseCodes.getReason("200"));
	}

	public HashMap<String,String> retrieveHeaders(String type) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Server", "Kristin Server");
		headers.put("Content-Type", type);
		return headers;
	}

	public String retrieveBody(Request request){
		if (request.getParmeters() != null && !request.getParmeters().isEmpty()){
			parameters = request.getParmeters();
			return viewFactory.forParameters(parameters).build();
		}
		return viewFactory.forContent(request.getURI()).build();
	}


}
