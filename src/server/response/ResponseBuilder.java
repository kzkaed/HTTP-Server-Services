package server.response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import routes.Routes;
import server.ArgsParser;
import server.request.Request;

public class ResponseBuilder {

	private final String VERSION_PROTOCOL = "HTTP/1.1";
	private final String CRLF = "\r\n";
	private final String STATUS_200 = "HTTP/1.1 200 OK" + CRLF;
	private final String STATUS_404 = "HTTP/1.1 404 Not Found" + CRLF;
	private final String STATUS_500 = "HTTP/1.1 500 Internal Server Error" + CRLF;
	private final String C404 = "404";

	private final String MY_PATH = "/Users/kristin-8thlight/repos2/HTTP-Server-Services";

	private Request request;
	private String statusLine;

	public ResponseBuilder(Request request) {
		this.request = request;
		this.statusLine = STATUS_200;

	}

	public String buildResponse() throws IOException {
		String response = "";
		String headers = buildResponseHeaders();
		
		if (request.getMethod().contentEquals("GET")) {
			String responseBody = getResponseBody(request.getURI());
			
			if (responseBody.isEmpty()) {
				response = STATUS_404 + headers + CRLF + "404 Not Found";
			} else {
				response = STATUS_200 + headers + CRLF + responseBody;
			}

		} else if (request.getMethod().contentEquals("POST")) {
			response = "HTTP/1.1 201 Created" + CRLF;
		} else if (request.getMethod().contentEquals("PUT")) {
			response = "HTTP/1.1 201 Created" + CRLF;
		} else if (request.getMethod().contentEquals("HEAD")) {
			response = "HTTP/1.1 200 OK" + CRLF;
		} else if (request.getMethod().contentEquals("OPTIONS")) {
			response = "HTTP/1.1 200 OK\r\nAllow:GET,HEAD,POST,OPTIONS,PUT"
					+ CRLF;
		}else{
			response = STATUS_500 + headers + CRLF + "500 " + ResponseCodes.getReason("500");
		}
		return response;
	}
	
	public String getStatusLine() {
		return statusLine;
	}

	public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
				+ "Accept-Ranges: bytes" + CRLF + "Content-Type: text/html\r\n";
		return headers;
	}

	public String findPath(String path) {
		Path currentRelativePath = Paths.get(path);
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		return relativePath;
	}

	public String getResponseBody(String uri) {
		String body = "";
		String relativePath = findPath("");
		String defaultDirectory = "/" + ArgsParser.PUBLIC_DIR;

		Routes route = new Routes();
		String routedPath = route.getRoute(uri);

		String path = relativePath + defaultDirectory + routedPath;

		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String str;
			while ((str = in.readLine()) != null) {
				body += str;
			}
			in.close();
		} catch (IOException e) {

		}

		return body;
	}

}
