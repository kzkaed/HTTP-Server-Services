package server.request;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.Hashtable;

import server.helpers.Utility;

public class ParserURI implements ParametersParser {
	private URI uri;
	private String requestUri; 
	private String protocol;
	private String query;
	private String reference;
	private String path;
	private String filename;
	private int port;
	private String authority;
	private Hashtable<String,String> parameterPairs;
	private String[] queryTokens;
	
	
	public ParserURI(String requestUri) throws URISyntaxException{
		this.uri = new URI(requestUri);
		this.protocol = uri.getSchemeSpecificPart();
		this.authority = uri.getAuthority();
		this.port = uri.getPort();
		this.path = uri.getPath();
		this.filename = uri.getFragment();
		this.query = uri.getQuery();
		this.parameterPairs = new Hashtable<String,String>();
	}
	
	public ParserURI(URI uri) {
		this.uri = uri;
		this.protocol = uri.getSchemeSpecificPart();
		this.authority = uri.getAuthority();
		this.port = uri.getPort();
		this.path = uri.getPath();
		this.filename = uri.getFragment();
		this.query = uri.getQuery();
		this.parameterPairs = new Hashtable<String,String>();
		
	}

	@Override
	public String getPath() {
		return this.path;
	}

	@Override
	public String getQuery() {
		return this.query;
	}

	@Override
	public String getFilename() {
		return this.filename;
	}

	@Override
	public Hashtable<String, String> getParameterPairs() {
		String delimiter;
		
		delimiter = server.constants.Constants.DELIMITER_AMPERSAND;
		String[] queryTokens = Utility.retreiveTokens(query, delimiter);
		for(int i = 0; i<queryTokens.length; i++){
			
			delimiter = server.constants.Constants.DELIMITER_EQUAL;
			String[] nameValueTokens = Utility.retreiveTokens(queryTokens[i],delimiter );
			for(int j = 0; j < nameValueTokens.length; j = j + 2 ){
				String parameterName = nameValueTokens[j];
				String parameterValue = nameValueTokens[j+1];
				parameterPairs.put( parameterName, parameterValue);
			}
		}
		return parameterPairs;
	}
	

}
