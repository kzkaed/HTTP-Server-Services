package server.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.nio.charset.*;

import server.constants.Constants;
import server.helpers.Utility;

public class RequestParser {

	private BufferedReader in;
	private ParametersParser subParser;

	public RequestParser(BufferedReader in) {
		this.in = in;
	}
	
	
	public Request parseRequest() throws IOException  {
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
		Hashtable<String,String> headers = new Hashtable<String,String>();
		String requestBody = "";
		String[] allOfRequest = readRemainingRequest();
		if(!allOfRequest[0].isEmpty()){
			headers= parseHeaders(allOfRequest[0]);
			if(allOfRequest.length > 1){
				requestBody = allOfRequest[1];
			}
		}
		return new Request(method, uri, protocolVersion, headers, requestLine, requestBody, parameters);	
	}
	
	
	private HashMap<String,Object> parseRequestLine(String requestLine)  {
	
		String[] requestTokens = Utility.retreiveTokens(requestLine,server.constants.Constants.DELIMITER_SPACE );
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
	
	public String[] readRemainingRequest() throws IOException{
		StringBuilder content = new StringBuilder();
		while(in.ready()){
			content.append((char) in.read());
		}
		return Utility.retreiveTokens(content.toString(), server.constants.Constants.HEADERS_END);
	}
	
	Hashtable<String,String> parseHeaders(String headers){
		String[] headerTokens = Utility.retreiveTokens(headers, Constants.CRLF); 
		Hashtable<String,String> headerPairs = new Hashtable<String,String>();
		for(int i = 0;i< headerTokens.length; i++){
			String headerLine = headerTokens[i];
			String[] pairs = Utility.retreiveTokens(headerLine, Constants.COLON);
			for(int j = 0; j < pairs.length; j= j+ 2){
				headerPairs.put(pairs[j], pairs[j + 1]);
			}	
		}
		return headerPairs;
	}
	void parseBody(){
		
	}

}
