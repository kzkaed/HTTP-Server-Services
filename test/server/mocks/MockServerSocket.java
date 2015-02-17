package server.mocks;



import java.io.IOException;
import java.net.ServerSocket;

import server.socket.ServerSocketService;


public class MockServerSocket implements ServerSocketService{

	
	private int port;
	private boolean closed;
	
	
	public MockServerSocket(int port) throws IOException{
		this.setPort(port);
	}
	

	@Override
	public Object accept() {
		this.closed = false;
		return true;
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
