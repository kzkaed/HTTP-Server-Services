package server.request;

import java.util.Hashtable;

public class Request {
	

	private String method;
	private String uri;
	private String protocolVersion;
	private Hashtable<String,String> headers;
	private String requestLine;
	private String requestBody;
	private Hashtable<String,String> parameters;
	
	public Request(String method,String uri, String protocolVersion, Hashtable<String,String> headers, String requestLine, String requestBody, Hashtable<String,String> parameters) {
		this.method = method;
		this.uri = uri;
		this.protocolVersion = protocolVersion;
		this.headers = headers;
		this.requestLine = requestLine;
		this.requestBody = requestBody;
		this.parameters = parameters;
	}	
			
	public String getMethod(){
		return this.method;
	}
	
	public String getProtocolVersion(){
		return this.protocolVersion;
	}
	
	public String getURI(){
		return this.uri;
	}
	
	public String getRequestLine(){
		return this.requestLine;
	}
	
	public Hashtable<String,String> getParmeters(){
		return this.parameters;
	}
	
	public Hashtable<String,String> getHeaders(){
		return this.headers;
	}
	
}







