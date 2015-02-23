package server.request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

//GET /hello.txt HTTP/1.1
//User-Agent: curl/7.16.3 libcurl/7.16.3 OpenSSL/0.9.7l zlib/1.2.3
//Host: www.example.com
//Accept: */*     


public class Request {
	
	private final String VERSION_PROTOCOL = "HTTP/1.1";
	private final String CRLF = "\r\n";
	private final String SPACE = "";

	
	private String method;
	private String uri;
	private String protocolVersion;
	private Hashtable<String,String> headers;
	private String requestLine;
	private String requestBody;
	
	
	
		
	public Request(String method,String uri, String protocolVersion, Hashtable<String,String> headers, String requestLine, String requestBody) {
		this.method = method;
		this.uri = uri;
		this.protocolVersion = protocolVersion;
		this.headers = headers;
		this.requestLine = requestLine;
		this.requestBody = requestBody;
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
	
	

}







