package server;

import java.io.IOException;

import log.SystemLogger;
import log.Logger;
import routes.AssetManager;
import server.handler.ClientHandler;
import server.response.DynamicAsset;
import server.response.FileStaticAsset;
import server.socket.ServerSocketService;
import server.socket.SocketService;

public class Server {
	public String document;
	public int port;
	private ServerSocketService service;
	private Logger logger;
	private AssetManager manager;
	
	
	public Server(ServerSocketService service, int port, String document, AssetManager manager){
		this(service, port, document, manager, new SystemLogger() );
	}
	
	public Server(ServerSocketService service, int port, String document, AssetManager manager, Logger logger){
		this.port = port;
		this.document = document;
		this.service = service;
		this.logger = logger;
		this.manager = manager;
		
	}
	
	public void start()  {
		manager.register(new FileStaticAsset());
		manager.register(new DynamicAsset());
		
		try{
			logServerInfomation();
			
			while(!service.isClosed()){ 
				logListening();
				
				SocketService socket = service.accept();
				new ClientHandler(socket,logger, manager).run();	
			}
			service.close();
		}catch(IOException ioe){
			logger.error(ioe.getStackTrace().toString());
			System.exit(1);
		}
		
	}
	
	public void logListening(){
		logger.log("Listening...");
	}
	
	public void logServerInfomation() {
		logger.log("Server Starting...");
	}

}

