package server.handler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

import log.LoggerService;
import server.request.HttpRequestParser;

public class SingleClientHandler {
	protected Socket socket;
	private BufferedReader in;
	private DataOutputStream out;

	public SingleClientHandler(Object object) throws IOException {
		this.socket = (Socket) object;
		this.in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		this.out = new DataOutputStream(socket.getOutputStream());
	}

	
	public void run() {
		String request = "";
		
		try {
			request = in.readLine();
			
			LoggerService.displayRequest(request);
			processHTTPRequest(request, out);
			socket.close();
		} catch (IOException ioe) {
			LoggerService.displayError(ioe.toString());
			ioe.printStackTrace();
		}

	}

	void processHTTPRequest(String request, DataOutputStream out) throws IOException {
		String response = new HttpRequestParser(request).parse();
		out.write(response.getBytes());
		LoggerService.displayInfo(response);

	}

}
