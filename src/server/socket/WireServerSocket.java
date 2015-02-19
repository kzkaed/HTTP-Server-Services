package server.socket;


import java.io.IOException;
import java.net.ServerSocket;


public class WireServerSocket implements ServerSocketService {
	private ServerSocket serverSocket;

	public WireServerSocket(ServerSocket socketService){
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
	
	public int getPort(){
		return serverSocket.getLocalPort();
	}

	public boolean isBound() {
		return serverSocket.isBound();
	}

}
