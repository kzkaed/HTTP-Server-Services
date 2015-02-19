package server.mocks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.socket.ServerSocketService;
import server.socket.SocketService;

public class MockServerSocketWrapper implements ServerSocketService {
	
	private MockServerSocket mockServerSocket;
	private boolean isClosed;
	private boolean isBound;
	
	public MockServerSocketWrapper(MockServerSocket serverSocket){
		this.mockServerSocket = serverSocket;
		this.isClosed = false;
	}
	
	@Override
	public SocketService accept() throws IOException {
		this.isClosed = false;
		this.isBound = true;
		return null;
	}

	@Override
	public void close() throws IOException {
		isClosed = true;
		isBound = false;
		
	}

	@Override
	public boolean isClosed() throws IOException {
		return isClosed;
	}

	@Override
	public boolean isBound() {
		
		return isBound;
	}

}
