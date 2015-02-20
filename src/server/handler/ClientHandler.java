package server.handler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import server.request.HttpRequestParser;
import server.socket.SocketService;
import log.Logger;


public class ClientHandler extends Thread{
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
			request = in.readLine();
			if (request != null){
				logger.log(request);
				String response = process(request);
				logger.log(response);
			}
		} catch (IOException ioe) {			
			System.err.println(ioe.getStackTrace());
		}
		
	}

	private String process(String request) throws IOException {
		String response = new HttpRequestParser(request).parse();
		out.write(response.getBytes());
		return response;
	}

}
