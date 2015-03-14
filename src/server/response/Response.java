package server.response;

import java.util.HashMap;
import java.util.Map;

import server.request.Request;
	
public class Response {
    
	private final String VERSION_PROTOCOL = server.Constants.VERSION_PROTOCOL;
	private final String CRLF = server.Constants.CRLF;
	private final String SPACE = server.Constants.SPACE;
    private final String COLON = server.Constants.COLON;
    
    private String bodyAsString;
    private byte[] body;
    private int statusCode;
    private String statusMessage;
    private HashMap<String, String> headers;
    private String headersStr;
    
    public Response (String bodyAsString, byte[] body, HashMap<String,String> headers, String headersStr){
    	this.bodyAsString = bodyAsString;
    	this.body = body;
    	this.headers = headers;
    	this.headersStr = headersStr;
    }
    
    public String buildResponse() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(VERSION_PROTOCOL);
    	sb.append(SPACE);
    	sb.append(statusCode);
    	sb.append(SPACE);
    	sb.append(statusMessage);
    	sb.append(CRLF);
    	for(Map.Entry<String, String> header : headers.entrySet()){
    		sb.append(header.getKey());
    		sb.append(COLON);
    		sb.append(header.getValue());
    		sb.append(CRLF);
    	}
    	sb.append(CRLF);
    	return sb.toString();

	}
    
    public String getHeaders(){
    	return headersStr;
    }
    
    public String getBody(){
    	return bodyAsString;
    }
    
   public void setBodyBytes(byte[] body){
	   this.body = body;
   }
    
    public byte[] getBodyBytes(){
    	return body;
    }
    
    public void setResponseStatusCode(int code){
    	this.statusCode = code;
    }
    
    public void setResponseStatusMessage(String message){
    	this.statusMessage = message;
    }
  
   

}
