package server.response;


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Set;

import routes.HtmlView;
import server.request.ParametersParser;
import server.request.ParametersParserURL;

public class DynamicAsset implements Asset{
	
	private final String QUERY = server.Constants.DELIMITER_QUERY;
	
	private String uri;
	private Hashtable<String,String> parameterPairs;
	private HtmlView html;

	public DynamicAsset ()  {}
	
	public String generate(String uri) throws MalformedURLException, UnsupportedEncodingException{
		ParametersParser parser = new ParametersParserURL(uri);
		String content = "";
		if (parser.getParameterNameValuePairs() != null){
			parameterPairs = parser.getParameterNameValuePairs();
			html = new HtmlView(parameterPairs);
		}else{
			html = new HtmlView(content);
		}
		
		return html.build();

		
	}



}
