package server.response;

import java.util.HashMap;

import server.request.Request;
	
public class Response {
    
	private final String VERSION_PROTOCOL = server.Constants.VERSION_PROTOCOL;
	
	private final String CRLF = server.Constants.CRLF;
	private final String SPACE = server.Constants.SPACE_E;
    private final String HEADER_COLON = server.Constants.COLON;
    private String responseBody;
    private byte[] body;
    private String statusCode;
    private String statusMessage;
    private HashMap<String, String> headers;
    private String headersStr;
    
    public Response (String responseBody, byte[] body, HashMap<String,String> headers, String headersStr){
    	this.responseBody = responseBody;
    	this.body = body;
    	this.headers = headers;
    	this.headersStr = headersStr;
    }
    
    public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
						+ "Accept-Ranges: bytes" + CRLF 
						+ "Content-Type: text/html; charset=UTF-8" + CRLF
						+ "Connection: Close" + CRLF;
		return headers;
	}
    
    public String getHeaders(){
    	return headersStr;
    }
    
    public String getBody(){
    	return responseBody;
    }
    
    public byte[] getBodyBytes(){
    	return body;
    }
  
   

}
