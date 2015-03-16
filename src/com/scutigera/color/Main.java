package com.scutigera.color;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;

import routes.AssetManager;
import server.ArgsParser;
import server.Context;
import server.Server;
import server.socket.WireServerSocket;

public class Main {

	public static void main(String[] args) throws IOException {
			
		int port = getPort(args);
		String publicDirectory = getPublicDirectory(args);
		
		Application.setContext(port, publicDirectory);
		AssetManager manager = Application.registerApplicationAssets(new AssetManager());
		
		ServerSocket serverSocket = new ServerSocket(port);
		new Server(new WireServerSocket(serverSocket), port, manager).start();
	}

	public static int getPort(String[] args){
		ArgsParser parser = new ArgsParser();
		Map<String, String> context = parser.parse(args);
		String portString = context.get("Port");
		return Integer.parseInt(portString);
	}
	
	public static String getPublicDirectory(String[] args){
		ArgsParser parser = new ArgsParser();
		Map<String, String> context = parser.parse(args);
		return context.get("Public Directory");
	}

}
