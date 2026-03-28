package server.request;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


public class ParserURI implements ParametersParser {
	private URI uri;
	private String query;
	private String path;
	private String filename;
	private Map<String,String> parameterPairs;
	
	
	public ParserURI(String requestUri) throws URISyntaxException{
		this.uri = new URI(requestUri);
		this.path = uri.getPath();
		this.filename = uri.getFragment();
		this.query = uri.getQuery();
		this.parameterPairs = new HashMap<>();
	}

	public ParserURI(URI uri) {
		this.uri = uri;
		this.path = uri.getPath();
		this.filename = uri.getFragment();
		this.query = uri.getQuery();
		this.parameterPairs = new HashMap<>();
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
	public Map<String, String> getParameterPairs() {
		String delimiter;
		
		delimiter = server.constants.Constant.DELIMITER_AMPERSAND;
		String[] queryTokens = query.split(delimiter);
		for(int i = 0; i<queryTokens.length; i++){
			
			delimiter = server.constants.Constant.DELIMITER_EQUAL;
			String[] nameValueTokens = queryTokens[i].split(delimiter);
			for(int j = 0; j < nameValueTokens.length; j = j + 2 ){
				String parameterName = nameValueTokens[j];
				String parameterValue = nameValueTokens[j+1];
				parameterPairs.put( parameterName, parameterValue);
			}
		}
		return parameterPairs;
	}
	
}
