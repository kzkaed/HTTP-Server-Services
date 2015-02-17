package server;



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
	
	
	public void start() throws Exception {
		LoggerService.displayServerStatus(null, port, document);
		
		while(!service.isClosed()){ 
			new SingleClientHandler(service.accept()).run();
			
		}
		service.close();
	}
	

}

