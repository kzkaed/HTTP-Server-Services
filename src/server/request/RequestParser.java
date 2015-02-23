package server.request;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.TimeZone;

import routes.Routes;
import server.ArgsParser;
import server.response.ResponseCodes;

public class RequestParser {
	final private String CRLF = "\r\n";
	final private String STATUS_200 = "HTTP/1.1 200 OK" + CRLF;
	final private String STATUS_404 = "HTTP/1.1 404 Not Found" + CRLF;
	final private String C404 = "404";
	
	final private String MY_PATH = "/Users/kristin-8thlight/repos2/HTTP-Server-Services";

	String request;
	BufferedReader in;
	

	String statusLine;

	public RequestParser(String request, BufferedReader in) {
		this.request = request;
		this.statusLine = STATUS_200;
		this.in = in;
	}

	public Request generateRequest(){
		HashMap<String,String> requestLineTokens = parseRequestLine(request);
		
		String body = getBody(request);
		String requestLine = request;
		String method = requestLineTokens.get("method");
		String uri = requestLineTokens.get("uri");
		String protocolVersion = requestLineTokens.get("protocolVersion");
		String[] contentsOfRequest;
		Hashtable<String,String> headers = null;

		return new Request(method, uri, body, headers, requestLine, protocolVersion);	
	}
	
	private HashMap<String,String> parseRequestLine(String request){
		String[] tokens = retreiveTokens(request, null);
		HashMap<String,String>requestLine = new HashMap<String,String>();
		requestLine.put("method", tokens[0]);
		requestLine.put("uri", tokens[1]);
		requestLine.put("protocolVersion", tokens[2]);
		return requestLine;	
	}
	

	public String buildResponse() {
		String response = "";
		if (request.indexOf("GET") > -1) {
			
			String headers = buildResponseHeaders();
			String body = getBody(request);
			if (body.isEmpty()){
				response = STATUS_404 + headers + CRLF + "404 Not Found";  
			}else{
				response = statusLine + headers + CRLF + body;		
			}
		
		
		} else if (request.indexOf("POST") > -1) {
			response = "HTTP/1.1 201 Created" + CRLF;
		} else if (request.indexOf("PUT") > -1) {
			response = "HTTP/1.1 201 Created" + CRLF;
		} else if (request.indexOf("HEAD") > -1) {
			response = "HTTP/1.1 200 OK" + CRLF;
		} else if (request.indexOf("OPTIONS") > -1) {
			response = "HTTP/1.1 200 OK\r\nAllow:GET,HEAD,POST,OPTIONS,PUT" + CRLF;
		}
		return response;
	}

	public String getStatusLine() {
		return statusLine;
	}
	
	

	public String[] retreiveTokens(String request, String delimiters) {
		delimiters = "[ ]+";
		String[] tokens = request.split(delimiters);
		
		return tokens;
	}
	


	
	public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
				+ "Accept-Ranges: bytes" + CRLF 
				+ "Content-Type: text/html\r\n";
		return headers;
	}
	
	String findPath(String path){
		Path currentRelativePath = Paths.get(path);
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		return relativePath;
	}
	
	
	
	String getBody(String request){
	    String body = "";
	    String relativePath = findPath("");
	    
	    HashMap<String,String> requestLine  = parseRequestLine(request);
	    String uriPath = requestLine.get("uri");
	    String defaultDirectory = "/"+ArgsParser.PUBLIC_DIR;
	    	    
	    
	    //Routes - default
	    Routes route = new Routes();
	    String routedPath = route.getRoute(uriPath);
	    
	    String path = relativePath + defaultDirectory +routedPath;
	   
	    try {
	        BufferedReader in = new BufferedReader(new FileReader(path));
	        String str;
	        while ((str = in.readLine()) != null) {
	            body +=str;     
	        }
	        in.close();
	    } catch (IOException e) {
	    	
	    }

	    return body;
	}

}
