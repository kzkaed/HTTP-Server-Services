package server.mocks;



import java.io.IOException;
import java.net.ServerSocket;

import server.socket.ServerSocketService;
import server.socket.SocketService;


public class MockServerSocket implements ServerSocketService{
	private int port;
	public boolean closed;
	private boolean bound;
	private SocketService socket;
	
	
	public MockServerSocket(int port) throws IOException{
		this.setPort(port);
		this.socket = new MockSocket();
	}
	

	@Override
	public SocketService accept() {
		this.closed = false;
		this.bound = true;
		return this.socket;
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


	@Override
	public boolean isBound() {
		return bound;
	}

}
