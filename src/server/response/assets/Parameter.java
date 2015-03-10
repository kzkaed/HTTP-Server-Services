package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Hashtable;

import routes.HtmlView;
import server.request.Request;

public class Parameter implements Asset {
	
	private Hashtable<String,String> parameters;
	private HtmlView html;
	
	Parameter () {}

	@Override
	public boolean canHandle(Request request) {
		return request.getURI().contentEquals("/parameters");
	}

	@Override
	public String render(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		if (request.getParmeters() != null && !request.getParmeters().isEmpty()){
			parameters = request.getParmeters();
			html = new HtmlView(parameters);
		}
		return html.build();
	}

}
