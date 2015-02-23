package server.request;

import java.util.HashMap;
import java.util.Hashtable;

//GET /hello.txt HTTP/1.1
//User-Agent: curl/7.16.3 libcurl/7.16.3 OpenSSL/0.9.7l zlib/1.2.3
//Host: www.example.com
//Accept: */*     


public class Request {
	
	private final String VERSION_PROTOCOL = "HTTP/1.1";
	private final String CRLF = "\r\n";
	private final String SPACE = " ";

	
	public String method;
	public String uri;
	public String body;
	public Hashtable<String,String> headers;
	public String requestLine;
	public String protocolVersion;
	
	
		
	public Request(String method,String uri, String body, Hashtable<String,String> headers, String requestLine, String protocolVersion) {
		this.method = method;
		this.uri = uri;
		this.body = body;
		this.headers = headers;
		this.requestLine = requestLine;
		this.protocolVersion = protocolVersion;
}	
		
	public Request(){
	 
	}

}







