package server.request;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author kristin-8thlight
 */
//GET /hello.txt HTTP/1.1
//User-Agent: curl/7.16.3 libcurl/7.16.3 OpenSSL/0.9.7l zlib/1.2.3
//Host: www.example.com
//Accept: */*     


public class Request {
	
	private final String VERSION = "HTTP/1.1";
	private final String RESPONSE_CODE_200 = "HTTP/1.1 200 OK\r\n";
	private final String CRLF = "\r\n";
	private final String SPACE = " ";

	
	private String method;
	private String uri;
	private String body;
	private Hashtable<String,String> headers;
	private String statusLine;
	
	
		
	public Request(String method,String uri, String body, Hashtable<String,String> headers, String statusLine) {
		this.method = method;
		this.uri = uri;
		this.body = body;
		this.statusLine = statusLine;
}	
		
	public Request(){
	 
	}

}







