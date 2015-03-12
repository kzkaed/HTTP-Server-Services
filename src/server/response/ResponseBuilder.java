package server.response;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import routes.AssetManager;
import server.request.Request;
import server.response.assets.Asset;

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

	public String buildResponse(AssetManager manager) throws IOException {
		String responseStr = "";
		String headers = buildResponseHeaders();
		String requestMethod = request.getMethod();
			
		if (requestMethod.contentEquals("GET")) {
			Asset asset = manager.getAsset(request);
			Response response = asset.execute(request);
			
			String responseBody = response.getBody();	
			//String responseBody = new String(response.getBodyBytes(), "UTF8");
			
			if (responseBody.isEmpty()) {
				responseStr = STATUS_404 + response.getHeaders() + CRLF + "404 Not Found";
			} else {
				responseStr = STATUS_200 + response.getHeaders() + CRLF + responseBody;
			}

		} else if (requestMethod.contentEquals("POST")) {
			responseStr = "HTTP/1.1 200 OK" + CRLF;
		} else if (requestMethod.contentEquals("PUT")) {	
			responseStr = "HTTP/1.1 200 OK" + CRLF;
		} else if (requestMethod.contentEquals("HEAD")) {	
			responseStr = "HTTP/1.1 200 OK" + CRLF;
		} else if (requestMethod.contentEquals("OPTIONS")) {	
			responseStr = "HTTP/1.1 200 OK\r\nAllow:GET,HEAD,POST,OPTIONS,PUT" + CRLF;
		}else{
			responseStr = STATUS_502 + headers + CRLF + "502 " + ResponseCodes.getReason("502");
		}
		return responseStr;
	}
	
	public boolean isMethodImplemented(){
		return  METHODS_IMPLEMENTED.contains(request.getMethod());
	}
	
	public String getStatusLine() {
		return statusLine;
	}

	public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
						+ "Accept-Ranges: bytes" + CRLF 
						+ "Content-Type: text/html" + CRLF;
						//+ "Connection: Close" + CRLF;
	
		return headers;
	}

}
