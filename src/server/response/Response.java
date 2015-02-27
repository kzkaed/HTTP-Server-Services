package server.response;

import java.util.HashMap;
	
public class Response {
    
	private final String VERSION_PROTOCOL = server.Constants.VERSION_PROTOCOL;
	
	private final String CRLF = server.Constants.CRLF;
	private final String SPACE = server.Constants.SPACE_E;
    private final String HEADER_COLON = server.Constants.COLON;
    private byte[] body;
    private String statusCode;
    private String statusMessage;
    private HashMap<String, String> headers;
    
    
    
    public Response(String statusCode, String statusMessage){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.body = body;
        this.headers = new HashMap<String, String>();
    }
    //or something like this.
    
    public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
				+ "Accept-Ranges: bytes" + CRLF 
				+ "Content-Type: text/html\r\n";
		return headers;
	}
  

}
