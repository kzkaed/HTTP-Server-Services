package server.request;

import java.util.Map;

public interface ParametersParser {

	public String getPath();
	public String getQuery();
	public String getFilename();
	public Map<String,String> getParameterPairs();

}
