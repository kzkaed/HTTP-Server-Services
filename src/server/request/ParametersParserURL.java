package server.request;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import server.Constants;
import server.Utilities;

public class ParametersParserURL implements ParametersParser {
	private URL url;
	private String requestUri; 
	
	private String protocol;

	private String query;
	
	private String reference;
	private String path;
	private String filename;

	private final String HOST = Constants.HOST;
	private int PORT_IN_USE = Constants.getPort();
	
	private Hashtable<String,String> parameterPairs;
	private String[] queryTokens;

	public ParametersParserURL(String requestUri) throws MalformedURLException{
		this.requestUri = requestUri;
		
		this.url = new URL( "http://" + HOST + PORT_IN_USE + requestUri );
		
		
		this.protocol = url.getProtocol();
		this.path = url.getPath();
		this.filename = url.getFile();
		this.reference = url.getRef();
		this.query = url.getQuery();
	
		this.parameterPairs = new Hashtable<String,String>();
	
	}
	
	
	
	public String getProtocol(){
		return this.protocol;
	}
	
	public String getPath(){
		return this.path;
	}
	
	public String getQuery(){
		return this.query;
	}
	
	public String getFilename(){
		return this.filename;
	}
	
	public String getRequestUri(){
		return this.requestUri;
	}
	
	public String getReference(){
		return this.reference;
	}
	
	@Override
	public Hashtable<String, String> getParameterNameValuePairs() {
		String delimiter;
		Utilities util = new Utilities();
		delimiter = server.Constants.DELIMITER_AMP;
		String[] queryTokens = util.retreiveTokens(query, delimiter);
	
		for(int i = 0;i<queryTokens.length;i++){
			delimiter = server.Constants.DELIMITER_EQUAL;
			String[] nameValueTokens = util.retreiveTokens(queryTokens[i],delimiter );
				for(int j = 0; j < nameValueTokens.length; j = j + 2 ){
					parameterPairs.put( nameValueTokens[j], nameValueTokens[j + 1]);
				}
			
		}
		return parameterPairs;
	}
	
	
	

}

