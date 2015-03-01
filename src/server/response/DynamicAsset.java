package server.response;


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Set;

import routes.HtmlView;
import server.request.ParametersParser;
import server.request.ParametersParserURL;

public class DynamicAsset {
	
	private final String QUERY = server.Constants.DELIMITER_QUERY;
	
	private String uri;
	private Hashtable<String,String> parameterPairs;
	private ParametersParser parser;
	private HtmlView html;

	public DynamicAsset (String uri) throws MalformedURLException, UnsupportedEncodingException {
		this.uri = uri;
		parser = new ParametersParserURL(uri);	

	}
	
	public String generatePage() throws MalformedURLException, UnsupportedEncodingException{
		if (parser.getParameterNameValuePairs() == null){
			System.out.println("QUERY " + parser.getQuery());
			System.out.println("P" + parser.getPath());
			System.out.println("uri" + uri);
			html = new HtmlView(uri);
		}else if (parser.getParameterNameValuePairs() != null){
			parameterPairs = parser.getParameterNameValuePairs();
			html = new HtmlView(parameterPairs);
		}else{
			html = new HtmlView();
		}
		
		return html.build();

		
	}

}
