package server.request;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class HttpRequestParser {
	final private String CRLF = "\r\n";
	final private String STATUS_200 = "HTTP/1.1 200 OK\r\n";
	final private String STATUS_400 = "HTTP/1.1 400 Tiddlywinks\r\n";
	
	String request;
	String body = "<!doctype html><html><head></head><body>hello</body></html>";
	
	String statusLine;
	
	
	public HttpRequestParser(String request){
		this.request = request;
		this.statusLine = STATUS_200;
		
	}

	public String parse() {
			String response = "";
			if (request.indexOf("GET") > -1){
				if (request == null){
					response = STATUS_400;
				}else{
				
				DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
				Calendar cal = Calendar.getInstance();
				String date = dateFormat.format(cal.getTime());
				
				response = statusLine 
						+ "Date: " +date + CRLF
						+ "Server: Kristin" + CRLF
						+ "Last-Modified: "+date + CRLF
						+ "ETag: 34aa387-d-1568eb00"+ CRLF
						+ "Accept-Ranges: bytes"+ CRLF
						+ "Content-Length: 59"+ CRLF
						+ "Vary: Accept-Encoding"+ CRLF
						+ "Content-Type: text/html" +CRLF+ CRLF + body;
				}
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
	
	public String getStatusLine(){
		return statusLine;
	}

}
//GET<space>URI<space>protocol<crlf> 
//Request-Line   = Method SP Request-URI SP HTTP-Version CRLF
//GET / HTTP/1.1

//POST /index.html HTTP/1.1
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
