package server.request;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import server.Context;
import server.constants.Constant;
import server.helpers.Utility;

public class ParametersParserURL implements ParametersParser {
	private URL url;
	private String requestUri; 
	private String protocol;
	private String query;
	private String path;
	private String filename;
	private int port;
	
	private final String HOST = Context.HOST;
	private int PORT_IN_USE = Context.PORT_IN_USE;
	
	private Hashtable<String,String> parameterPairs;

	public ParametersParserURL(String requestUri) throws MalformedURLException, UnsupportedEncodingException{
		this.requestUri = requestUri;
		this.url = new URL( "http://" + HOST + PORT_IN_USE + requestUri );
		this.protocol = url.getProtocol();
		this.path = url.getPath();
		this.filename = url.getFile();
		this.query = url.getQuery();
		this.parameterPairs = new Hashtable<String,String>();
	}
	
	
	public String decode(String stringToDecode) throws UnsupportedEncodingException{
		return java.net.URLDecoder.decode(stringToDecode,Constant.UTF_8);
	}
	
	
	public String getProtocol(){
		return this.protocol;
	}
	
	public int getPort(){
		return this.port;
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
		
	@Override
	public Hashtable<String, String> getParameterPairs() {
		if(query != null){
			String delimiter;
			Utility util = new Utility();
			
			delimiter = server.constants.Constant.DELIMITER_AMPERSAND;
			String[] queryTokens = util.retreiveTokens(query, delimiter);
			for(int i = 0; i<queryTokens.length; i++){
				
				delimiter = server.constants.Constant.DELIMITER_EQUAL;
				String[] nameValueTokens = util.retreiveTokens(queryTokens[i],delimiter );
				for(int j = 0; j < nameValueTokens.length; j = j + 2 ){
					String parameterName = "";
					String parameterValue = "";
					try {
						parameterName = decode(nameValueTokens[j]);
						parameterValue = decode(nameValueTokens[j+1]);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					parameterPairs.put( parameterName, parameterValue);
				}
			}
		}
		return parameterPairs;
	}
	
	
	

}

