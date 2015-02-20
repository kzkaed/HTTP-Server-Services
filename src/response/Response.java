package response;

import java.util.Hashtable;

	
public class Response {
    
	private final String VERSION = "HTTP/1.1";
	private final String RESPONSE_LINE = "HTTP/1.1 200 OK\r\n";
	private final String CRLF = "\r\n";
	private final String SPACE = " ";
    String headerColon = ": ";
    private byte[] body;
    private String statusCode;
    private String statusMessage;
    private Hashtable<String, String> headers;
    
    
    
    public Response(String statusCode, String statusMessage){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.body = new byte[0];
        this.headers = new Hashtable<String, String>();
    }
    //or something like this.
    
    public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
				+ "Accept-Ranges: bytes" + CRLF 
				+ "Content-Type: text/html\r\n";
		return headers;
	}
    

	public String[] retreiveTokens(String request) {
		String delimiters = "[ ]+";
		String[] tokens = request.split(delimiters);
		
		return tokens;
	}

}
