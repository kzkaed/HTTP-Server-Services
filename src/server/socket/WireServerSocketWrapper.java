package server.socket;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WireServerSocketWrapper implements ServerSocketService{
	private ServerSocket serverSocket;

	public WireServerSocketWrapper(ServerSocket socketService){
		this.serverSocket = socketService;
	}

	@Override
	public SocketService accept() throws IOException {
		return new WireSocket(serverSocket.accept());
	}

	@Override
	public void close() throws IOException {
		serverSocket.close();
	}
	
	public boolean isClosed(){
		return serverSocket.isClosed();
	}
	

}
