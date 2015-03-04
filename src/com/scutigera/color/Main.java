package com.scutigera.color;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;

import routes.AssetManager;
import server.ArgsParser;
import server.Constants;
import server.Server;
import server.socket.WireServerSocket;

public class Main {

	public static void main(String[] args) throws IOException {
		AssetManager manager = new AssetManager();
		manager.register(new Color());
		
		
		ArgsParser parser = new ArgsParser();
		Map<String, String> context = parser.parse(args);
		String portString = context.get("Port");
		String publicDirectory = context.get("Public Directory");
		int port = Integer.parseInt(portString);
		Constants.setPort(port);		
		ServerSocket serverSocket = new ServerSocket(port);
		new Server(new WireServerSocket(serverSocket),port,publicDirectory, manager).start();

	}

}
