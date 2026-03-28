package server.request;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import server.constants.Constant;

public class ParametersParserURL implements ParametersParser {
	private URL url;
	private String requestUri;
	private String protocol;
	private String query;
	private String path;
	private String filename;
	private int port;

	private Map<String,String> parameterPairs;

	public ParametersParserURL(String requestUri, String host, int port) throws MalformedURLException, UnsupportedEncodingException{
		this.requestUri = requestUri;
		try {
			this.url = new URI("http://" + host + port + requestUri).toURL();
		} catch (URISyntaxException e) {
			throw new MalformedURLException(e.getMessage());
		}
		this.protocol = url.getProtocol();
		this.path = url.getPath();
		this.filename = url.getFile();
		this.query = url.getQuery();
		this.parameterPairs = new HashMap<>();
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
	public Map<String, String> getParameterPairs() {
		if(query != null){
			String[] queryTokens = query.split(server.constants.Constant.DELIMITER_AMPERSAND);
			for(int i = 0; i<queryTokens.length; i++){
				String[] nameValueTokens = queryTokens[i].split(server.constants.Constant.DELIMITER_EQUAL);
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

