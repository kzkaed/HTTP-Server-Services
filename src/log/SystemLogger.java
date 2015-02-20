package log;

import server.socket.ServerSocketService;

public class SystemLogger implements Logger{

	@Override
	public void log(String message) {
		System.out.println(message);		
	}

	@Override
	public void error(String message) {
		System.err.println(message);
		
	}
	
	public void displayServerStatus(ServerSocketService serverSocket, int port, String document){
		System.out.println("Server Starting...");
		System.out.println(serverSocket.getClass().getName());
		System.out.println("Port: " + port);
		System.out.println("Root Directory: " + document);		
	}

}
