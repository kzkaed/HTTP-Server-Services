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

import server.ArgsParser;

public class RequestParser {
	final private String CRLF = "\r\n";
	final private String STATUS_200 = "HTTP/1.1 200 OK" + CRLF;
	final private String MY_PATH = "/Users/kristin-8thlight/repos2/HTTP-Server-Services";

	String request;
	BufferedReader in;
	String body = "<!doctype html><html><head></head><body>Mushroom in the Rain</body></html>";

	String statusLine;

	public RequestParser(String request, BufferedReader in) {
		this.request = request;
		this.statusLine = STATUS_200;
		this.in = in;
	}

	public Request generateRequest(){
		HashMap<String,String> requestLineTokens = parseRequestLine(request);
		
		String body = "";
		String requestLine = request;
		String method = requestLineTokens.get("method");
		String uri = requestLineTokens.get("uri");
		String protocolVersion = requestLineTokens.get("protocolVersion");
		String[] contents;
		Hashtable<String,String> headers = null;

		return new Request(method, uri, body, headers, requestLine, protocolVersion);	
	}
	
	private HashMap<String,String> parseRequestLine(String request){
		String[] tokens = retreiveTokens(request);
		HashMap<String,String>requestLine = new HashMap<String,String>();
		requestLine.put("method", tokens[0]);
		requestLine.put("uri", tokens[1]);
		requestLine.put("protocolVersion", tokens[2]);
		return requestLine;	
	}
	
	
	
	
	
	public String buildResponse() {
		String response = "";
		if (request.indexOf("GET") > -1) {
			//return statusline, headers, and body, if body. 
			//GET is Read Only, action load body and return with response code and headers
			
			
			String headers = buildResponseHeaders();
			response = statusLine + headers + CRLF + getBody(request);		
	
		
		
		} else if (request.indexOf("POST") > -1) {
			response = "HTTP/1.1 201 Created" + CRLF;
		} else if (request.indexOf("PUT") > -1) {
			response = "HTTP/1.1 201 Created" + CRLF;
		} else if (request.indexOf("HEAD") > -1) {
			//like GET, response code and header only no body
			response = "HTTP/1.1 200 OK" + CRLF;
		} else if (request.indexOf("OPTIONS") > -1) {
			response = "HTTP/1.1 200 OK\r\nAllow:GET,HEAD,POST,OPTIONS,PUT" + CRLF;
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
	
	String findPath(String path){
		Path currentRelativePath = Paths.get(path);
		String relativePath = currentRelativePath.toAbsolutePath().toString();
		return relativePath;
	}
	
	String getDocumentBody(){
		String body = this.body;
		return body;
		
	}
	
	String getBody(String request){
	    String body = "";
	    String relativePath = findPath("");
	    
	    HashMap<String,String> requestLine  = parseRequestLine(request);
	    String uriPath = requestLine.get("uri");
	    String defaultDirectory = "/"+ArgsParser.PUBLIC_DIR;
	    	    
	    
	    //Routes - default
	    String path = relativePath + defaultDirectory +  "/index.html";
	    if(!uriPath.contentEquals("/")  ){
	    	
	    	path = relativePath + defaultDirectory +uriPath;
	    }else if (uriPath.contentEquals("/test/index")){
	    	String route = "/test/index.html";
	    	path = relativePath + defaultDirectory + route;
	    	
	    }
	    
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
