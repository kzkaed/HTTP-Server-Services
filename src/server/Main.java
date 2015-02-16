package server;

import java.net.ServerSocket;
import java.util.Map;

import server.socket.WireServerSocketWrapper;


public class Main {

public static void main(String[] args) throws Exception{
		
		ArgsParser parser = new ArgsParser();
		Map<String, String> context = parser.parse(args);
		String portString = context.get("Port");
		String publicDirectory = context.get("Public Directory");
		int port = Integer.parseInt(portString);
		
		ServerSocket serverSocket = new ServerSocket(port);
		new Server(serverSocket,port,publicDirectory).start();
	}

}
