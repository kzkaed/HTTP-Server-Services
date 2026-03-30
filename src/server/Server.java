package server;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import server.response.assets.Parameter;
import log.SystemLogger;
import log.Logger;
import routes.AssetManager;
import server.handler.ClientHandler;
import server.response.assets.DirectoryAsset;
import server.response.assets.DynamicAsset;
import server.response.assets.StaticAsset;
import server.response.assets.ImageAsset;
import server.response.assets.Options;
import server.response.assets.Post;
import server.response.assets.Put;
import server.response.assets.DynamicPathExt;
import server.response.assets.StaticPathExt;
import server.socket.ServerSocketService;
import server.socket.SocketService;
import views.HtmlViewFactory;
import views.ViewFactory;

public class Server {
	private int port;
	private ServerSocketService service;
	private Logger logger;
	private AssetManager manager;
	private final String publicDir;
	private final String host;
	private final ExecutorService threadPool;


	public Server(ServerSocketService service, int port, AssetManager manager, String publicDir, String host){
		this(service, port, manager, new SystemLogger(), publicDir, host);
	}

	public Server(ServerSocketService service, int port, AssetManager manager, Logger logger, String publicDir, String host){
		this.port = port;
		this.service = service;
		this.logger = logger;
		this.manager = manager;
		this.publicDir = publicDir;
		this.host = host;
		this.threadPool = Executors.newFixedThreadPool(10);
	}
	
	public void start()  {

		registerServerAssets();

		try{
			logServerInfomation();

			while(!service.isClosed()){
				logListening();

				SocketService socket = service.accept();
				threadPool.execute(new ClientHandler(socket, logger, manager, host, port));
			}
		}catch(IOException ioe){
			try {
				if(!service.isClosed()){
					logger.error(Arrays.toString(ioe.getStackTrace()));
				}
			} catch (IOException e) {
				logger.error(Arrays.toString(ioe.getStackTrace()));
			}
		}

	}

	public void stop() {
		try {
			logger.log("Server Shutting Down...");
			service.close();
			threadPool.shutdown();
			if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
				threadPool.shutdownNow();
			}
		} catch (IOException ioe) {
			logger.error(Arrays.toString(ioe.getStackTrace()));
		} catch (InterruptedException ie) {
			threadPool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	public void registerServerAssets(){
		ViewFactory viewFactory = new HtmlViewFactory();
		manager.register(new StaticAsset(publicDir));
		manager.register(new DynamicAsset(viewFactory));
		manager.register(new DirectoryAsset(publicDir, viewFactory));
		manager.register(new Parameter(viewFactory));
		manager.register(new ImageAsset(publicDir));
		manager.register(new StaticPathExt(publicDir));
		manager.register(new Options());
		manager.register(new Post());
		manager.register(new Put());
		manager.register(new DynamicPathExt(viewFactory));
	}
	
	public void logListening(){
		logger.log("Listening... on port " + port);
	}
	
	public void logServerInfomation() {
		logger.log("Server Starting...");
	}

}

