package server.request;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

public class ParametersParserURL implements ParametersParser {
	private URL url;
	private String urlStr; 
	
	private String protocol;
	private String authority;
	private String host;
	private String query;
	private int port;
	private String ref;
	private String path;
	private String filename;

	private Hashtable<String,String> pairs;
	private Hashtable<String,Hashtable> pathParams; 

	public ParametersParserURL(String urlStr) throws MalformedURLException{
		this.urlStr = urlStr;
		this.url = new URL(urlStr);//need to check this via validation?
		
		this.protocol = url.getProtocol();
		this.authority = url.getAuthority();
		this.host = url.getHost();
		this.port = url.getPort();
		this.path = url.getPath();
		this.filename = url.getFile();
		this.ref = url.getRef();
		this.query = url.getQuery();
	
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
	public Hashtable<String, String> getPairs() {
		return null;
	}
	

}

