package server.mocks;



import java.io.IOException;
import java.net.ServerSocket;

import server.socket.ServerSocketService;
import server.socket.SocketService;


public class MockServerSocket implements ServerSocketService{
	private int port;
	private boolean closed;
	SocketService socket = new MockSocket();
	
	
	public MockServerSocket(int port) throws IOException{
		this.setPort(port);
	}
	

	@Override
	public SocketService accept() {
		this.closed = false;
		return socket;
	}

	@Override
	public void close() throws IOException {
		this.closed = true;
		
	}


	@Override
	public boolean isClosed() throws IOException {
		return closed;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}

}
