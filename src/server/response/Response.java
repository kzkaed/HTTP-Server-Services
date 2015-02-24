package server.response;

import java.util.HashMap;
	
public class Response {
    
	private final String VERSION = "HTTP/1.1";
	private final String RESPONSE_LINE = "HTTP/1.1 200 OK\r\n";
	private final String CRLF = "\r\n";
	private final String SPACE = "\\s";
    String headerColon = ": ";
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
