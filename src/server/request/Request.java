package server.request;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private String method;
	private String uri;
	private String protocolVersion;
	private Map<String,String> headers;
	private String requestLine;
	private String requestBody;
	private Map<String,String> parameters;
	private String fullUri;

	public Request(){
		this.parameters = new HashMap<>();
	}

	public Request(String method, String uri,Map<String,String> headers, Map<String,String>parameters, String body ){
		this.method = method;
		this.uri = uri;
		this.headers = headers;
		this.requestBody = body;
		this.parameters = parameters;
	}
	
	public Request(String method,String uri, String protocolVersion, Map<String,String> headers, String requestLine, String requestBody, Map<String,String> parameters) {
		this.method = method;
		this.uri = uri;
		this.protocolVersion = protocolVersion;
		this.headers = headers;
		this.requestLine = requestLine;
		this.requestBody = requestBody;
		this.parameters = parameters;
	}	
	
	public Request(String uri){
		this.uri = uri;
	}
	
	public String getFullUri(){
		return this.fullUri;
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
	
	public Map<String,String> getParmeters(){
		return this.parameters;
	}

	public void setParameter(String key, String value){
		this.parameters.put(key, value);
	}

	public Map<String,String> getHeaders(){
		return this.headers;
	}

	public void setURI(String uri) {
		this.uri = uri;		
	}
	
	public void setMethod(String method){
		this.method = method;
	}
	
}







