package log;

import java.net.ServerSocket;
import java.util.logging.*;

import server.socket.ServerSocketService;

public class LoggerService {
	
	
	

	public static void displayServerStatus(ServerSocket serverSocket, int port, String document){
		System.out.println("Server Starting...");
		System.out.println(serverSocket);
		System.out.println("Port: " + port);
		System.out.println("DOCUMENT ROOT" + document);
		
		
	}
	
	
	

	
	
	
}
