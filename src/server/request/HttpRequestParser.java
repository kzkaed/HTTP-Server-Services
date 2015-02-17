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
			}else if (request.indexOf("PUT") > -1){
				response = "HTTP/1.1 200 OK\r\n";
			}else if (request.indexOf("HEAD")> -1){
				response = "HTTP/1.1 200 OK\r\n";
			}else if (request.indexOf("OPTIONS")> -1){
				response = "HTTP/1.1 200 OK\r\nAllow:GET,HEAD,POST,OPTIONS,PUT\r\n";
			}
			
			return response;	
		
	}

}
//GET<space>URI<space>protocol<crlf> 
//Request-Line   = Method SP Request-URI SP HTTP-Version CRLF
//GET / HTTP/1.1

//POST /bugreport.php HTTP/1.1
//Host: www.example.com
//Content-Type: application/x-www-form-urlencoded
//Content-Length: [size of the request body]

/*Request       = Request-Line              ; Section 5.1
*(( general-header        ; Section 4.5
 | request-header         ; Section 5.3
 | entity-header ) CRLF)  ; Section 7.1
CRLF
[ message-body ]          ; Section 4.3
*/
