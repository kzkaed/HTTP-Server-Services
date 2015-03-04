package server.response;


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Set;

import routes.HtmlView;
import server.request.ParametersParser;
import server.request.ParametersParserURL;
import server.request.Request;

public class DynamicShowParamsAsset implements Asset{
		
	private Hashtable<String,String> parameters;
	private HtmlView html;

	public DynamicShowParamsAsset ()  {}
	
	public String render(Request request) throws MalformedURLException, UnsupportedEncodingException{
				
		if (request.getParmeters() != null && !request.getParmeters().isEmpty()){
			parameters = request.getParmeters();
			html = new HtmlView(parameters);
		}else{
			html = new HtmlView("Test");
		}
		
		return html.build();

		
	}

	@Override
	public boolean canHandle(Request request) {
		System.out.println(request.getURI());
		if(request.getURI().contentEquals("/")){
			return true;
		}else if (request.getURI().contains("showParams")){
			return true;
		}else if (request.getURI().contentEquals("parameters")){
			return true;
		}else { 
			return false;
		}
			
	}



}
