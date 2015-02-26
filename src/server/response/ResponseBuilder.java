package server.response;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import server.request.Request;

public class ResponseBuilder {

	
	private final String CRLF = server.Constants.CRLF;
	private final String STATUS_200 = server.Constants.STATUS_200;
	private final String STATUS_404 = server.Constants.STATUS_404;
	private final String STATUS_502 = server.Constants.STATUS_502;
	
	
	private final List<String> METHODS_IMPLEMENTED = Arrays.asList("GET","POST","PUT","HEAD","OPTIONS"); 
	
	private Request request;
	private String statusLine;
	

	public ResponseBuilder(Request request) {
		this.request = request;
		this.statusLine = STATUS_200;
	}

	public String buildResponse() throws IOException {
		String response = "";
		String headers = buildResponseHeaders();
		String requestMethod = request.getMethod();
		
		
		if (requestMethod.contentEquals("GET")) {
			FileStaticAsset asset = new FileStaticAsset();
			String responseBody = asset.getResponseBody(request.getURI());
			
			if (responseBody.isEmpty()) {
				response = STATUS_404 + headers + CRLF + "404 Not Found";
			} else {
				response = STATUS_200 + headers + CRLF + responseBody;
			}

		} else if (requestMethod.contentEquals("POST")) {
			response = "HTTP/1.1 201 Created" + CRLF;
		} else if (requestMethod.contentEquals("PUT")) {
			response = "HTTP/1.1 201 Created" + CRLF;
		} else if (requestMethod.contentEquals("HEAD")) {
			response = "HTTP/1.1 200 OK" + CRLF;
		} else if (requestMethod.contentEquals("OPTIONS")) {
			response = "HTTP/1.1 200 OK\r\nAllow:GET,HEAD,POST,OPTIONS,PUT"
					+ CRLF;
		}else{
			response = STATUS_502 + headers + CRLF + "502 " + ResponseCodes.getReason("502");
		}
		return response;
	}
	
	public boolean isMethodImplemented(){
		return  METHODS_IMPLEMENTED.contains(request.getMethod());
	}
	
	public String getStatusLine() {
		return statusLine;
	}

	public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
				+ "Accept-Ranges: bytes" + CRLF + "Content-Type: text/html\r\n";
		return headers;
	}

}
