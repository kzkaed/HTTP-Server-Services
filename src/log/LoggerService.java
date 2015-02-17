package log;



import server.socket.ServerSocketService;

public class LoggerService {

	public static void displayServerStatus(ServerSocketService serverSocket, int port, String document){
		System.out.println("Server Starting...");
		System.out.println(serverSocket.getClass().getName());
		System.out.println("Port: " + port);
		System.out.println("Root Directory: " + document);		
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
