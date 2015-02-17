package server.handler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

import log.LoggerService;
import server.request.HttpRequestParser;
import server.socket.SocketService;

public class SingleClientHandler {
	protected SocketService socket;
	private BufferedReader in;
	private DataOutputStream out;

	public SingleClientHandler(SocketService service) throws IOException {
		this.socket = service;
		this.in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		this.out = new DataOutputStream(socket.getOutputStream());
	}

	


	public void run() {
		String request = "";
		
		try {
			request = in.readLine();
			processHTTPRequest(request, out);
			socket.close();
		} catch (IOException ioe) {			
			System.err.println("Can't readline in from socket.." + ioe);
		}
		LoggerService.displayInfo(request);
		

	}

	void processHTTPRequest(String request, DataOutputStream out) throws IOException {
		String response = new HttpRequestParser(request).parse();
		out.write(response.getBytes());
		LoggerService.displayInfo(response);

	}

}
