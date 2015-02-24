package server.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

public class RequestParser {

	private BufferedReader in;

	public RequestParser(BufferedReader in) {
		this.in = in;
	}
	
	public Request parseRequest()  {
		String requestLine = readRequestLine();
		
		if ( requestLine == null ){
			return new Request( "" ,"" ,"" ,null ,"" ,null );
		}

		HashMap<String,String> requestLineTokens = parseRequestLine(requestLine);

		String method = requestLineTokens.get("method");
		String uri = requestLineTokens.get("uri");
		String protocolVersion = requestLineTokens.get("protocolVersion");
		
		String[] allOfRequest = null;
		Hashtable<String,String> headers = null;
		String requestBody = null;
		
		return new Request(method, uri, protocolVersion, headers, requestLine, requestBody);	
	}
	
	public String[] retreiveTokens(String request, String delimiters) {
		String[] tokens = request.split(delimiters);
		return tokens;
	}
	
	private HashMap<String,String> parseRequestLine(String requestLine) {
		String delimiters = "[ ]+";
		String[] tokens = retreiveTokens(requestLine, delimiters);
		
		HashMap<String,String>requestLineTokens = new HashMap<String,String>();
		requestLineTokens.put("method", tokens[0]);
		requestLineTokens.put("uri", tokens[1]);
		requestLineTokens.put("protocolVersion", tokens[2]);

		return requestLineTokens;	
	}


	private String readRequestLine(){
		String requestLine = "";
		try {
			requestLine = in.readLine();
		} catch (IOException e) {
			System.err.println("readRequestLine error" + e.getStackTrace());
		}
		return requestLine;
	}
	

}
