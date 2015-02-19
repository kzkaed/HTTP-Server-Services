package server.request;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class HttpRequestParser {
	final private String CRLF = "\r\n";
	final private String STATUS_200 = "HTTP/1.1 200 OK\r\n";
	final private String STATUS_400 = "HTTP/1.1 400 Tiddlywinks\r\n";

	String request;
	String body = "<!doctype html><html><head></head><body>Mushroom in the Rain</body></html>";

	String statusLine;

	public HttpRequestParser(String request) {
		this.request = request;
		this.statusLine = STATUS_200;
	}

	public String parse() {
		String response = "";
		if (request.indexOf("GET") > -1) {
			String headers = buildResponseHeaders();
			System.out.println("HERE2");
			response = statusLine + headers + CRLF + body;
				
			
		} else if (request.indexOf("POST") > -1) {
			response = "HTTP/1.1 200 OK\r\n";
		} else if (request.indexOf("PUT") > -1) {
			response = "HTTP/1.1 200 OK\r\n";
		} else if (request.indexOf("HEAD") > -1) {
			response = "HTTP/1.1 200 OK\r\n";
		} else if (request.indexOf("OPTIONS") > -1) {
			response = "HTTP/1.1 200 OK\r\nAllow:GET,HEAD,POST,OPTIONS,PUT\r\n";
		}
		return response;
	}

	public String getStatusLine() {
		return statusLine;
	}

	public String[] retreiveTokens(String request) {
		String delimiters = "[ ]+";
		String[] tokens = request.split(delimiters);
		return tokens;
	}

	public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
				+ "Accept-Ranges: bytes" + CRLF 
				+ "Content-Type: text/html\r\n";
		return headers;
	}

}
