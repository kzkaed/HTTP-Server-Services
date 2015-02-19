package server.handler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import log.LoggerService;
import server.request.HttpRequestParser;
import server.socket.SocketService;

public class SingleClientHandler extends Thread{
	protected SocketService socket;
	private BufferedReader in;
	private DataOutputStream out;

	public SingleClientHandler(SocketService service) throws IOException {
		this.socket = service;
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new DataOutputStream(socket.getOutputStream());
	}

	public void run() {
		String request = "";
		
		try {
			request = in.readLine();
			if (request != null){
				LoggerService.displayInfo(request);
				String response = process(request);
				LoggerService.displayInfo(response);
			}
			socket.close();
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
