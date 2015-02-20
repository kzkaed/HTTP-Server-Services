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
import java.util.TimeZone;

public class HttpRequestParser {
	final private String CRLF = "\r\n";
	final private String STATUS_200 = "HTTP/1.1 200 OK\r\n";
	final private String STATUS_400 = "HTTP/1.1 400 Tiddlywinks\r\n";
	final private String MY_PATH = "/Users/kristin-8thlight/repos2/HTTP-Server-Services";

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
			
			response = statusLine + headers + CRLF + getContent(request);		
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
	
	public HashMap<String,String> buildRequestStatusLine(String request){
		String[] tokens = retreiveTokens(request);
		HashMap<String,String>requestStatusLine = new HashMap<String,String>();
		requestStatusLine.put("method", tokens[0]);
		requestStatusLine.put("uri", tokens[1]);
		requestStatusLine.put("protocolVersion", tokens[2]);
		return requestStatusLine;	
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
	
	String getContent(String request){
	    String content = "";
	    String realativePath = findPath("");
	    HashMap<String,String> grabit  = buildRequestStatusLine(request);
	    String uriPath = grabit.get("uri");
	    String publicDirectory = "/public";
	    System.out.println(uriPath);
	    String path = realativePath + publicDirectory +  "/index.html";
	    if(!uriPath.contentEquals("/")  ){
	    	System.out.println("here");
	    	path = realativePath + publicDirectory +uriPath;
	    }
	    System.out.println(path);
	    try {
	        BufferedReader in = new BufferedReader(new FileReader(path));
	        String str;
	        while ((str = in.readLine()) != null) {
	            content +=str;     
	        }
	        in.close();
	    } catch (IOException e) {
	    	
	    }
	    System.out.println(content);
	    return content;
	}

}
