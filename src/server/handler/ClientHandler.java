package server.handler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import routes.AssetManager;
import server.request.Request;
import server.request.RequestParser;
import server.response.Response;
import server.response.ResponseBuilder;
import server.response.ResponseSender;
import server.socket.SocketService;
import log.Logger;


public class ClientHandler {
	protected SocketService socket;
	private BufferedReader in;
	private DataOutputStream out;
	private Logger logger;
	private Request request;
	private Response response;
	private AssetManager manager;
	

	public ClientHandler(SocketService socket, Logger logger, AssetManager manager) throws IOException {
		this.manager = manager;
		this.socket = socket;
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new DataOutputStream(socket.getOutputStream());
		this.logger = logger;
	}

	public void run() {
		
		try {	
			request = new RequestParser(in).parseRequest();
			
			response = new ResponseBuilder(request).buildResponse(manager);
			new ResponseSender(response, out).send();	
			
			logger.log(request.getRequestLine());
			logger.log(response.getResponseAsString());
		
			socket.close();
		} catch (IOException ioe) {			
			System.err.println(ioe.getStackTrace());
		}
		
	}

	public Response getResponse(){
		return this.response;
	}
	
	public Request getRequest(){
		return this.request;
	}


}
