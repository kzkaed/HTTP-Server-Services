package server.request;


import java.util.Hashtable;

public interface ParametersParser {
	
	public String getPath();
	public String getQuery();
	public String getFilename();
	public String getReference();
	public Hashtable<String,String> getParameterNameValuePairs();

}
