package server.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.nio.charset.*;

import server.Constants;
import server.Utilities;

public class RequestParser {

	private BufferedReader in;
	private ParametersParser subParser;

	public RequestParser(BufferedReader in) {
		this.in = in;
	}
	
	
	public Request parseRequest() throws UnsupportedEncodingException  {
		String requestLine = readRequestLine();
		
		if ( requestLine == null ){
			return new Request( "" ,"" ,"" ,null ,"" ,null,null );
		}

		HashMap<String,Object> requestLineTokens = parseRequestLine(requestLine);

		String method = requestLineTokens.get("method").toString();
		String uri = requestLineTokens.get("uri").toString();
		String protocolVersion = requestLineTokens.get("protocolVersion").toString();

		Hashtable<String,String> parameters;
		if(requestLineTokens.containsKey("parameters")){
			parameters =  (Hashtable<String, String>) requestLineTokens.get("parameters");	
		}else{
			parameters =  new Hashtable<String,String>();
		}
		
		
		String[] allOfRequest = null;
		Hashtable<String,String> headers = null;
		String requestBody = null;
		
		return new Request(method, uri, protocolVersion, headers, requestLine, requestBody, parameters);	
	}
	
	
	private HashMap<String,Object> parseRequestLine(String requestLine)  {
	
		String[] requestTokens = Utilities.retreiveTokens(requestLine,server.Constants.DELIMITER_SPACE );
		String requestUri = requestTokens[1];
		
		try {
			subParser = new ParametersParserURL(requestUri);
		} catch (MalformedURLException e) {		
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String uriPath = subParser.getPath();
		String query = subParser.getQuery();
		
		HashMap<String,Object>requestLineTokens = new HashMap<String,Object>();
		requestLineTokens.put("method", requestTokens[0]);
		requestLineTokens.put("uri", uriPath);
		requestLineTokens.put("protocolVersion", requestTokens[2]);
		
		if (query != null){
			requestLineTokens.put("query", query );
			requestLineTokens.put("parameters", (Hashtable<String,String>)subParser.getParameterPairs());
		}
		
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
	
	void parseRequestLine(){	
	}
	
	void parseHeaders(){
		
	}
	void parseBody(){
		
	}

}
