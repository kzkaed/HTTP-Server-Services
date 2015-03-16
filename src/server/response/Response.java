package server.response;

import java.util.HashMap;
import java.util.Map;

import server.request.Request;
	
public class Response {
    
	private final String VERSION_PROTOCOL = server.constants.Constants.VERSION_PROTOCOL;
	private final String CRLF = server.constants.Constants.CRLF;
	private final String SPACE = server.constants.Constants.SPACE;
    private final String COLON = server.constants.Constants.COLON;
    
    private String bodyAsString;
    private byte[] body;
    private int statusCode;
    private String statusMessage;
    private HashMap<String, String> headers;
    
    public Response (String bodyAsString, byte[] body, HashMap<String,String> headers, int statusCode, String statusMessage){
    
    	this.bodyAsString = bodyAsString;
    	this.body = body;
    	this.headers = headers;
    	this.statusCode = statusCode;
    	this.statusMessage = statusMessage;
    }
 
    public String getStatusLine(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(VERSION_PROTOCOL);
    	sb.append(SPACE);
    	sb.append(statusCode);
    	sb.append(SPACE);
    	sb.append(statusMessage);
    	sb.append(CRLF);
    	return sb.toString();
    }
    public String getHeaders() {
    	StringBuilder sb = new StringBuilder();
    	for(Map.Entry<String, String> header : headers.entrySet()){
    		sb.append(header.getKey());
    		sb.append(COLON);
    		sb.append(header.getValue());
    		sb.append(CRLF);
    	}
    	sb.append(CRLF);
    	return sb.toString();

	}
    
    public String getResponseAsString(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(getStatusLine());
    	sb.append(getHeaders());
    	sb.append(getBody());
    	return sb.toString();
    }
  
    
    public String getBody(){
    	return bodyAsString;
    }
    
    
    public byte[] getBodyBytes(){
    	return body;
    }
    
   public void setBodyBytes(byte[] body){
	   this.body = body;
   }

    
    public void setResponseStatusCode(int code){
    	this.statusCode = code;
    }
    
    public void setResponseStatusMessage(String message){
    	this.statusMessage = message;
    }
  
   

}
