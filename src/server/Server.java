package server;

import java.io.IOException;
import java.net.ServerSocket;

import log.LoggerService;
import server.socket.ServerSocketService;

public class Server {
	private String document;
	private int port;
	private ServerSocket serverSocket;
	private ClientHandler handler;
	
	public static boolean controlSwitch = true;
	
	public Server(ServerSocket serverSocket, int port, String document){
		this.port = port;
		this.document = document;
		this.serverSocket = serverSocket;
	}
	
	
	
	public void start() throws IOException {
		LoggerService.displayServerStatus(serverSocket, port, document);
		
		while(!serverSocket.isClosed()){ 
			new SingleClientHandler(serverSocket.accept()).run();
		}
		serverSocket.close();
	}
	

}

