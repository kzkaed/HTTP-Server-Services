package server.response;


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Set;

import routes.HtmlView;
import server.request.ParametersParser;
import server.request.ParametersParserURL;
import server.request.Request;

public class DynamicAsset implements Asset{
		
	private Hashtable<String,String> parameters;
	private HtmlView html;

	public DynamicAsset ()  {}
	
	public String render(Request request) throws MalformedURLException, UnsupportedEncodingException{
				
		if (request.getParmeters() != null && !request.getParmeters().isEmpty()){
			parameters = request.getParmeters();
			html = new HtmlView(parameters);
		}else{
			html = new HtmlView("Test");
		}
		
		return html.build();

		
	}



}
