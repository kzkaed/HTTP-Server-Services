package server.request;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import server.constants.Constant;

public class ParametersParserRegex implements ParametersParser {
	private String requestUri;


	ParametersParserRegex(String requestUri){
		this.requestUri = requestUri;
	}
	@Override
	public String getPath() {
		String[] uriTokens = requestUri.split(server.constants.Constant.DELIMITER_QUERY);
		String path = uriTokens[0];
		return path;
	}

	@Override
	public String getQuery() {
		String[] uriTokens = requestUri.split(server.constants.Constant.DELIMITER_QUERY);
		String query = uriTokens[1];
		return query;
	}

	@Override
	public String getFilename() {
		return requestUri;
	}

	@Override
	public Map<String, String> getParameterPairs() {
		Map<String,String> parameterPairs = new HashMap<>();
		String[] queryTokens = getQuery().split(server.constants.Constant.DELIMITER_AMPERSAND);

		for(int i = 0; i<queryTokens.length; i++){
			String[] nameValueTokens = queryTokens[i].split(server.constants.Constant.DELIMITER_EQUAL);
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
		return java.net.URLDecoder.decode(stringToDecode,Constant.UTF_8);
	}
	
	

}
