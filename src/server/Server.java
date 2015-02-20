package server;

import java.io.IOException;
import java.util.logging.Logger;

import log.LoggerService;
import log.SystemLogger;
import server.handler.SingleClientHandler;
import server.socket.ServerSocketService;
import server.socket.SocketService;
import server.socket.WireSocket;


public class Server {
	public String document;
	public int port;
	private ServerSocketService service;
	private log.Logger logger;
	
	public Server(ServerSocketService service, int port, String document){
		this(service, port, document, new SystemLogger());
	}
	
	public Server(ServerSocketService service, int port, String document, log.Logger logger){
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
				new SingleClientHandler(socket,logger).run();	
				socket.close();
			}
			service.close();
		}catch(IOException ioe){
			logger.error(ioe.getStackTrace().toString());
			System.exit(1);
		}
	}
	
}

