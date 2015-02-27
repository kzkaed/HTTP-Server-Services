package server.response;


import java.net.MalformedURLException;
import java.util.Hashtable;

import routes.HtmlView;
import server.request.ParametersParser;
import server.request.ParametersParserURL;

public class DynamicAsset {
	
	private final String QUERY = server.Constants.DELIMITER_QUERY;
	
	private String uri;
	private Hashtable<String,String> parameterPairs;
	private ParametersParser params;
	private HtmlView html;

	public DynamicAsset (String uri) throws MalformedURLException {
		this.uri = uri;
		params = new ParametersParserURL(uri);		
	}
	
	public String generatePage() throws MalformedURLException{
		
		if (uri.contains(QUERY)){
			parameterPairs = params.getParameterNameValuePairs();	
			html = new HtmlView(parameterPairs);
		}
		html = new HtmlView(uri);
		return html.build();
	}

}
