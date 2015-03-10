package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Hashtable;

import routes.HtmlView;
import server.request.Request;
import server.response.Response;

public class Parameter implements Asset {
	
	private Hashtable<String,String> parameters;
	private HtmlView html;
	
	Parameter () {}

	@Override
	public boolean canHandle(Request request) {
		System.out.println("canHandle" + request.getURI());
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
