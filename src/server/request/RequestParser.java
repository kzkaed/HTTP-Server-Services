package server.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Hashtable;

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

	private BufferedReader in;



	public RequestParser(BufferedReader in) {
		this.in = in;
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

	public Request parseRequest()  {
		String requestLine = readRequestLine();

		HashMap<String,String> requestLineTokens = parseRequestLine(requestLine);

		String method = requestLineTokens.get("method");
		String uri = requestLineTokens.get("uri");
		String protocolVersion = requestLineTokens.get("protocolVersion");
		
		String[] allOfRequest = null;
		Hashtable<String,String> headers = null;
		String requestBody = null;//on POST ?id=3&fun=snow
		
		return new Request(method, uri, protocolVersion, headers, requestLine, requestBody);	
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
	


	public String[] retreiveTokens(String request, String delimiters) {
		String[] tokens = request.split(delimiters);
		return tokens;
	}


	
	
	
	

	public void close() throws Exception {
		System.out.println("Closing");
		
	}

}
