package server.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Hashtable;

import server.Utilities;

public class RequestParser {

	private BufferedReader in;
	private ParametersParser subParser;

	public RequestParser(BufferedReader in) {
		this.in = in;
		
	}
	
	
	public Request parseRequest()  {
		String requestLine = readRequestLine();
		
		if ( requestLine == null ){
			return new Request( "" ,"" ,"" ,null ,"" ,null,null );
		}

		HashMap<String,Object> requestLineTokens = parseRequestLine(requestLine);

		String method = requestLineTokens.get("method").toString();
		String uri = requestLineTokens.get("uri").toString();
		String protocolVersion = requestLineTokens.get("protocolVersion").toString();

		Hashtable<String,String> parameters = null;
		if(requestLineTokens.containsKey("parameters")){
			parameters =  (Hashtable<String, String>) requestLineTokens.get("parameters");
		}
		
		String[] allOfRequest = null;
		Hashtable<String,String> headers = null;
		String requestBody = null;
		
		return new Request(method, uri, protocolVersion, headers, requestLine, requestBody, parameters);	
	}
	
		void parseRequestLine(){
			
		}
		
		void parseHeaders(){
			
		}
		void parseBody(){
			
		}
	private HashMap<String,Object> parseRequestLine(String requestLine) {
		
		Utilities util = new Utilities();
		String[] requestTokens = util.retreiveTokens(requestLine,server.Constants.DELIMITER_SPACE );
		String requestUri = requestTokens[1];
		String query = "";
		try {
			subParser = new ParametersParserURL(requestUri);
			query = subParser.getQuery();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	
		HashMap<String,Object>requestLineTokens = new HashMap<String,Object>();
		
		requestLineTokens.put("method", requestTokens[0]);
		requestLineTokens.put("uri", requestUri);
		requestLineTokens.put("protocolVersion", requestTokens[2]);
		
		if (query != null){
			requestLineTokens.put("query", query );
			requestLineTokens.put("parameters", (Hashtable<String,String>)subParser.getParameterNameValuePairs());
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
	
	public Hashtable<String, String> getParameterNameValuePairs(String query) {
		Hashtable<String,String> parameterPairs = new Hashtable<String,String>();
		Utilities util = new Utilities();
		String delimiters = "&";
		String[] queryTokens = util.retreiveTokens(query, delimiters);
	
		for(int i = 0;i<queryTokens.length;i++){
			String[] nameValueTokens = util.retreiveTokens(queryTokens[i], "=");
				for(int j = 0; j < nameValueTokens.length; j = j + 2 ){
					parameterPairs.put( nameValueTokens[j], nameValueTokens[j + 1]);
				}
			
		}
		return parameterPairs;
	}
	
	

}
