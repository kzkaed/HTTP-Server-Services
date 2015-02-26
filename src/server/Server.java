package server;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;

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
			
			logServerInfomation();
			
			while(!service.isClosed()){ 
				logListening();
				SocketService socket = service.accept();
				new ClientHandler(socket,logger).run();	
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

