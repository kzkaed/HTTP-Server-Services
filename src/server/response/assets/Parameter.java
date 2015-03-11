package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Hashtable;

import server.request.Request;
import server.response.Response;
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
	public Response render(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		if (request.getParmeters() != null && !request.getParmeters().isEmpty()){
			parameters = request.getParmeters();
			html = new HtmlView(parameters);
		}
		
		Response response = new Response(html.build(),html.build().getBytes() , null);
								
		return response;
	}

}
