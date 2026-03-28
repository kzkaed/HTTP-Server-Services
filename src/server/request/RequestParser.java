package server.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import server.constants.Constant;

public class RequestParser {

	private BufferedReader in;
	private ParametersParser subParser;
	private final String host;
	private final int port;

	public RequestParser(BufferedReader in, String host, int port) {
		this.in = in;
		this.host = host;
		this.port = port;
	}
	
	
	public Request parseRequest() throws IOException  {
		String requestLine = readRequestLine();
		
		if ( requestLine == null ){
			return new Request( "" ,"" ,"" ,null ,"" ,null,null );
		}

		ParsedRequestLine parsed = parseRequestLine(requestLine);

		String method = parsed.method;
		String uri = parsed.uri;
		String protocolVersion = parsed.protocolVersion;
		Map<String,String> parameters = parsed.parameters;
		Map<String,String> headers = new HashMap<>();
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
	
	
	private ParsedRequestLine parseRequestLine(String requestLine)  {

		String[] requestTokens = requestLine.split(server.constants.Constant.DELIMITER_SPACE);
		String requestUri = requestTokens[1];

		try {
			subParser = new ParametersParserURL(requestUri, host, port);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String uriPath = subParser.getPath();
		String query = subParser.getQuery();

		Map<String,String> parameters;
		if (query != null){
			parameters = subParser.getParameterPairs();
		} else {
			parameters = new HashMap<>();
		}

		return new ParsedRequestLine(requestTokens[0], uriPath, requestTokens[2], parameters);
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
		return content.toString().split(server.constants.Constant.HEADERS_END);
	}
	
	Map<String,String> parseHeaders(String headers){
		String[] headerTokens = headers.split(Constant.CRLF);
		Map<String,String> headerPairs = new HashMap<>();
		for(int i = 0;i< headerTokens.length; i++){
			String headerLine = headerTokens[i];
			String[] pairs = headerLine.split(Constant.COLON);
			for(int j = 0; j < pairs.length; j= j+ 2){
				headerPairs.put(pairs[j], pairs[j + 1]);
			}	
		}
		return headerPairs;
	}
	void parseBody(){
		
	}

}
