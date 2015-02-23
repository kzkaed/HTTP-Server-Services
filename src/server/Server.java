package server;

import java.io.IOException;

import log.SystemLogger;
import log.Logger;
import server.handler.ClientHandler;
import server.socket.ServerSocketService;
import server.socket.SocketService;

public class Server {
	public String document;
	public int port;
	private ServerSocketService service;
	private Logger logger;
	
	public Server(ServerSocketService service, int port, String document){
		this(service, port, document, new SystemLogger());
	}
	
	public Server(ServerSocketService service, int port, String document, Logger logger){
		this.port = port;
		this.document = document;
		this.service = service;
		this.logger = logger;	
	}
		
	public void start()  {
		try{
			logger.log("Server Starting...");
			
			while(!service.isClosed()){ 
				logger.log("Listening...");
				SocketService socket = service.accept();
				new ClientHandler(socket,logger).run();	
			}
			
		}catch(IOException ioe){
			logger.error(ioe.getStackTrace().toString());
			System.exit(1);
		}
	}
	
}

