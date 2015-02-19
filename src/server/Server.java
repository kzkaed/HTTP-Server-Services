package server;



import java.io.IOException;

import log.LoggerService;
import server.handler.SingleClientHandler;
import server.socket.ServerSocketService;


public class Server {
	public String document;
	public int port;
	ServerSocketService service;
	
	public Server(ServerSocketService service, int port, String document){
		this.port = port;
		this.document = document;
		this.service = service;
	}
	
	
	public void start()  {
		try{
			
			LoggerService.displayServerStatus(service, port, document);
			
			while(!service.isClosed()){ 
				LoggerService.displayInfo("listening..." + service.isBound());
				new SingleClientHandler(service.accept()).run();			
			}
			service.close();
		}catch(IOException ioe){
			LoggerService.displayError(ioe.getStackTrace().toString()+ "here");
			System.exit(1);
		}
	}
	

}

