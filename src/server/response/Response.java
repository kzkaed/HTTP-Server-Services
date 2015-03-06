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
    
    public Response (String responseBody, byte[] body, HashMap<String,String> headers){
    
    	this.responseBody = responseBody;
    	
    }
    
    
    public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
						+ "Accept-Ranges: bytes" + CRLF 
						+ "Content-Type: text/html; charset=UTF-8" + CRLF
						+ "Connection: Close" + CRLF;
						
		
		
		/*ETag: "3f80f-1b6-3e1cb03b" 
		Content-Type: text/html; charset=UTF-8 
		Content-Length: 131 
		Accept-Ranges: bytes 
		Connection: close*/
		return headers;
	}
  

}
