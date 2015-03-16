package server.response;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseSender {
	private Response response;
	private OutputStream out;
	
	public ResponseSender(Response response, OutputStream out){
	 this.out = out;
	 this.response = response;
	}
		
	public void send() throws IOException{
		out.write(response.getStatusLine().getBytes());
		out.flush();
		out.write(response.getHeaders().getBytes());
		out.flush();
		out.write(response.getBodyBytes());
		
	}

}
