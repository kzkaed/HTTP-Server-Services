package server.request;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Hashtable;
import routes.Routes;
import server.ArgsParser;


/*
 * The normal procedure for parsing an HTTP message is to read the
start-line into a structure, read each header field into a hash table
by field name until the empty line, and then use the parsed data to
determine if a message body is expected.  

If a message body has been
indicated, then it is read as a stream until an amount of octets
equal to the message body length is read or the connection is closed.
*/



public class RequestParser {
	final private String CRLF = "\r\n";
	final private String STATUS_200 = "HTTP/1.1 200 OK" + CRLF;
	final private String STATUS_404 = "HTTP/1.1 404 Not Found" + CRLF;
	final private String C404 = "404";
	
	final private String MY_PATH = "/Users/kristin-8thlight/repos2/HTTP-Server-Services";

	private String request;
	private BufferedReader in;
	private String statusLine;

	public RequestParser(String request, BufferedReader in) {
		this.statusLine = STATUS_200;
		this.in = in;
		this.request = request;
	}

	public Request parseRequest() throws IOException{
		//String request = in.readLine();
		
		
		HashMap<String,String> requestLineTokens = parseRequestLine(request);

		String requestLine = request;
		String method = requestLineTokens.get("method");
		String uri = requestLineTokens.get("uri");
		String protocolVersion = requestLineTokens.get("protocolVersion");
		System.out.println("requestLine " + requestLine);
		System.out.println("method " + method);
		System.out.println("uri " + uri);
		String[] allOfRequest;
		Hashtable<String,String> headers = null;
		String requestBody = "";//on POST
		
		
		
		return new Request(method, uri, protocolVersion, headers, requestLine, requestBody);	
	}
	
	private HashMap<String,String> parseRequestLine(String request){
		String delimiters = "[ ]+";
		String[] tokens = retreiveTokens(request, delimiters);
		
		HashMap<String,String>requestLine = new HashMap<String,String>();
		requestLine.put("method", tokens[0]);
		requestLine.put("uri", tokens[1]);
		requestLine.put("protocolVersion", tokens[2]);
		return requestLine;	
	}
	


	public String[] retreiveTokens(String request, String delimiters) {
		String[] tokens = request.split(delimiters);
		return tokens;
	}
	

	public String buildResponse() throws IOException {
		String response = in.readLine();
		if (request.indexOf("GET") > -1) {
			
			String headers = buildResponseHeaders();
			String responseBody = getResponseBody(request);
			if (responseBody.isEmpty()){
				response = STATUS_404 + headers + CRLF + "404 Not Found";  
			}else{
				response = statusLine + headers + CRLF + responseBody;		
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
	
	
	
	String getResponseBody(String request){
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
