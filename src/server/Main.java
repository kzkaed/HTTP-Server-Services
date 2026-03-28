package server;

import java.net.ServerSocket;
import java.util.Map;

import routes.AssetManager;
import server.socket.WireServerSocket;


public class Main {

public static void main(String[] args) throws Exception{
		int port = getPort(args);
		String publicDirectory = getPublicDirectory(args);

		Context.setContext(port, publicDirectory);

		AssetManager manager = new AssetManager();
		ServerSocket serverSocket = new ServerSocket(port);

		Server server = new Server(new WireServerSocket(serverSocket), port, manager, publicDirectory, Context.HOST);

		Runtime.getRuntime().addShutdownHook(new Thread(server::stop));

		server.start();
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
