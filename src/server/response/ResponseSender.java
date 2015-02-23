package server.response;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseSender {
	private String response;
	private OutputStream out;
	
	ResponseSender(String response, OutputStream out){
	 this.out = out;
	 this.response = response;
	}
		
	void send() throws IOException{
		out.write(response.getBytes());
	}

}
