package routes;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Hashtable;

import server.request.ParametersParser;
import server.request.ParametersParserURL;
public class Routes {
	
	private static final String TEST_ROUTE = "/test/index";
	
	private static final String DEFAULT_INDEX = "/index.html";
	private static final String TEST_FILE = "/test.html";
	private static final String TEST_ROUTE_OBJECT = "/test/test";
	private static final String QUERY = server.Constants.DELIMITER_QUERY;
	
	private String uri;
	private Hashtable<String,String> parameterPairs;
	private String path;
	private ParametersParser params;
	private HtmlView html;

	public Routes (String uri) {
		this.uri = uri;
	}
	
	public String getRoute() {
		
	String route;
		
	
		if(uri.contentEquals(TEST_ROUTE)){
			route = "/test/index.html";
		}else if (uri.contentEquals(TEST_FILE)){
			route = TEST_FILE;		
		
		}else if (uri.contentEquals("/") ){
			route = DEFAULT_INDEX;
			
		}else{
			route = uri;
			
		}
		
		return route;
	}
	

}
