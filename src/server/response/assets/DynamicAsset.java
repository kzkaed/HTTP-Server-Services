package server.response.assets;


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import server.request.ParametersParser;
import server.request.ParametersParserURL;
import server.request.Request;
import server.response.Response;
import server.response.ResponseCodes;
import views.HtmlView;

public class DynamicAsset implements Asset{
		
	protected Hashtable<String,String> parameters;
	protected String body;
	
	public DynamicAsset() {}
	
	@Override
	public boolean canHandle(Request request) {
			return request.getURI().contains("showParams"); 
		
	}
	
	public Response execute(Request request) throws MalformedURLException, UnsupportedEncodingException{
		body = render(request);
		return new Response(body, body.getBytes(), determineHeaders("text/html"), 200, ResponseCodes.getReason("200"));
	}

	public HashMap<String,String> determineHeaders(String type) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Server", "Kristin Server");
		headers.put("Content-Type", type);
		return headers;
	}

	public String render(Request request){
		HtmlView html = null;
		if (request.getParmeters() != null && !request.getParmeters().isEmpty()){
			parameters = request.getParmeters();
			html = new HtmlView(parameters);
			
		}else{
			html = new HtmlView(request.getURI());
		}
		return html.build();
		
	}


}
