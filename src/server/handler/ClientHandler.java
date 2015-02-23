package server.handler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import server.request.RequestParser;
import server.socket.SocketService;
import log.Logger;


public class ClientHandler {
	protected SocketService socket;
	private BufferedReader in;
	private DataOutputStream out;
	private Logger logger;

	public ClientHandler(SocketService socket, Logger logger) throws IOException {
		this.socket = socket;
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new DataOutputStream(socket.getOutputStream());
		this.logger = logger;
	}

	public void run() {
		String request = "";
		
		try {
	
			//Request request = new RequestParser(in).parse;
			//Response response = new ResponseBuilder(request).build;
			//new ResponseSender(response, out).send;
			
			request = in.readLine();
			if (request != null){
				String response = new RequestParser(request, in).buildResponse();
				out.write(response.getBytes());	
				
				logger.log(request);
				logger.log(response);
			}
			socket.close();
		} catch (IOException ioe) {			
			System.err.println(ioe.getStackTrace());
		}
		
	}



}
