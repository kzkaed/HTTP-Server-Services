package server.request;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

import server.Constants;
import server.Utilities;

public class ParametersParserRegex implements ParametersParser {
	private String requestUri;

	
	ParametersParserRegex(String requestUri){
		this.requestUri = requestUri;
	}
	@Override
	public String getPath() {
		String[] uriTokens = Utilities.retreiveTokens(requestUri,server.Constants.DELIMITER_QUERY);
		String path = uriTokens[0];
		return path;
	}

	@Override
	public String getQuery() {
		String[] uriTokens = Utilities.retreiveTokens(requestUri,server.Constants.DELIMITER_QUERY);
		String query = uriTokens[1];
		return query;
	}

	@Override
	public String getFilename() {
		return requestUri;
	}

	@Override
	public Hashtable<String, String> getParameterPairs() {
		Hashtable<String,String> parameterPairs = new Hashtable<String,String>();
		String[] queryTokens = Utilities.retreiveTokens(getQuery(), server.Constants.DELIMITER_AMPERSAND);
	
		for(int i = 0; i<queryTokens.length; i++){
			String[] nameValueTokens = Utilities.retreiveTokens(queryTokens[i], server.Constants.DELIMITER_EQUAL);
				for(int j = 0; j < nameValueTokens.length; j = j + 2 ){
					try {
						parameterPairs.put( decode(nameValueTokens[j]), decode(nameValueTokens[j + 1]));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
		}
		return parameterPairs;
	}
	
	
	private String decode(String stringToDecode) throws UnsupportedEncodingException{
		return java.net.URLDecoder.decode(stringToDecode,Constants.UTF_8);
	}
	
	

}
