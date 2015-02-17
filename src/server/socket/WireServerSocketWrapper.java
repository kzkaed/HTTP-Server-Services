package server.socket;


import java.net.ServerSocket;
import java.net.Socket;

public class WireServerSocketWrapper implements ServerSocketService{
	
	
	private ServerSocket serverSocket;

	public WireServerSocketWrapper(ServerSocket socketService){
		this.serverSocket = socketService;
	}

	@Override
	public Socket accept() throws Exception {
		return serverSocket.accept();
	}

	@Override
	public void close() throws Exception {
		serverSocket.close();
	}
	
	public boolean isClosed(){
		return serverSocket.isClosed();
	}
	

}
