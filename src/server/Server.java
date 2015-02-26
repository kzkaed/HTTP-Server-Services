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
	

		
	public void start() throws URISyntaxException  {
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
		String loopbackHost = InetAddress.getLoopbackAddress().getHostName().toString();
		String host = "not able to be determined";
		String ipAddress = "0.0.0.0";
		URL url = null;
		URI uri = null;
		String userInfo = null;
		String path = null; 
		String query = null;
		String fragment = null;
		try {
			host = InetAddress.getLocalHost().getHostName().toString();
			ipAddress = Inet4Address.getLocalHost().getHostAddress();
			url = new URL("http",host,port,"");
			uri = new URI("http",userInfo,host,port,path,query,fragment);
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		logger.log("machine loopback hostname : "+loopbackHost);
		logger.log("machine hostname : " +host);
		logger.log("machine ip address: " +ipAddress);
		logger.log("port : "  +port);
		logger.log("url " + url);
		logger.log("uri " + uri);
		
	}
	
	
	
	
}

