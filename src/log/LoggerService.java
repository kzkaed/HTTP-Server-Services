package log;

import java.net.ServerSocket;

public class LoggerService {

	public static void displayServerStatus(ServerSocket serverSocket, int port, String document){
		System.out.println("Server Starting...");
		System.out.println(serverSocket);
		System.out.println("Port: " + port);
		System.out.println("DOCUMENT ROOT" + document);		
	}
	
	public static void displayRequest(String request){
		System.out.println("Processing request "+ request);
	}
	
	public static void displayError(String error){
		System.err.println(error);
		
	}
	
	public static void displayInfo(String content){
		System.out.println(content);
	}
	

	
	

	
	
	
}
