package server.request;

public class HttpRequestParser {
	String request;
	
	public HttpRequestParser(String request){
		this.request = request;
	}

	public String parse() {
			String response = "";
			if (request.indexOf("GET") > -1){
				response = "HTTP/1.1 200 OK\r\n";
			}else if (request.indexOf("POST") > -1){
				response = "HTTP/1.1 200 OK\r\n";
			}
			return response;	
		
	}

}
