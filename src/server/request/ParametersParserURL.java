package server.request;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import server.FinalConstants;
import server.Utilities;

public class ParametersParserURL implements ParametersParser {
	private URL url;
	private String requestUri; 
	
	private String protocol;
	private String authority;
	private String host;
	private String query;
	private int port;
	private String ref;
	private String path;
	private String filename;

	private final String HOST = FinalConstants.HOST;
	private final int PORT = 5000;
	
	private Hashtable<String,String> parameterPairs;
	private String[] queryTokens;

	public ParametersParserURL(String requestUri) throws MalformedURLException{
		this.requestUri = requestUri;
		this.url = new URL(requestUri);
		//System.out.println(url);
		
		this.protocol = url.getProtocol();
		this.authority = url.getAuthority();
		this.host = url.getHost();
		this.port = url.getPort();
		this.path = url.getPath();
		this.filename = url.getFile();
		this.ref = url.getRef();
		this.query = url.getQuery();
		
		
		parameterPairs = new Hashtable<String,String>();
	
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
	
	public String getRef(){
		return this.ref;
	}
	
	@Override
	public Hashtable<String, String> getParameterNameValuePairs() {
		Utilities util = new Utilities();
		String delimiters = "&";
		String[] queryTokens = util.retreiveTokens(query, delimiters);
	
		for(int i = 0;i<queryTokens.length;i++){
			String[] nameValueTokens = util.retreiveTokens(queryTokens[i], "=");
				for(int j = 0; j < nameValueTokens.length; j = j + 2 ){
					parameterPairs.put( nameValueTokens[j], nameValueTokens[j + 1]);
				}
			
		}
		return parameterPairs;
	}
	
	
	

}

