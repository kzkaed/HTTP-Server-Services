package server;

import java.net.ServerSocket;
import java.util.Map;

import routes.AssetManager;
import server.socket.WireServerSocket;


public class Main {

public static void main(String[] args) throws Exception{
		AssetManager manager = new AssetManager();
		
		ArgsParser parser = new ArgsParser();
		Map<String, String> context = parser.parse(args);
		String portString = context.get("Port");
		String publicDirectory = context.get("Public Directory");
		int port = Integer.parseInt(portString);
		Constants.setPort(port);
		Constants.setPublicDirectory(publicDirectory);
		
		
		ServerSocket serverSocket = new ServerSocket(port);
		new Server(new WireServerSocket(serverSocket),port,publicDirectory,manager).start();

	}

}
