package server;

import java.io.IOException;

import server.response.assets.Parameter;
import log.SystemLogger;
import log.Logger;
import routes.AssetManager;
import server.handler.ClientHandler;
import server.response.assets.DirectoryAsset;
import server.response.assets.DynamicAsset;
import server.response.assets.FileNotFound;
import server.response.assets.GetFileStaticAsset;
import server.response.assets.Get;
import server.response.assets.ImageAsset;
import server.response.assets.Options;
import server.response.assets.Post;
import server.response.assets.Put;
import server.response.assets.TestStatic;
import server.socket.ServerSocketService;
import server.socket.SocketService;

public class Server {
	public int port;
	private ServerSocketService service;
	private Logger logger;
	private AssetManager manager;
	
	
	public Server(ServerSocketService service, int port, AssetManager manager){
		this(service, port, manager, new SystemLogger() );
	}
	
	public Server(ServerSocketService service, int port, AssetManager manager, Logger logger){
		this.port = port;
		this.service = service;
		this.logger = logger;
		this.manager = manager;	
	}
	
	public void start()  {
		
		registerServerAssets();

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

	public void registerServerAssets(){
		manager.register(new GetFileStaticAsset());
		manager.register(new DynamicAsset());
		manager.register(new DirectoryAsset());
		manager.register(new Parameter());
		manager.register(new ImageAsset());
		manager.register(new TestStatic());
		manager.register(new Options());
		manager.register(new Post());
		manager.register(new Put());
	}
	
	public void logListening(){
		logger.log("Listening...");
	}
	
	public void logServerInfomation() {
		logger.log("Server Starting...");
	}

}

