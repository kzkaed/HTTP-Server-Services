package server.mocks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.socket.ServerSocketService;
import server.socket.SocketService;

public class MockServerSocketWrapper implements ServerSocketService {
	
	private MockServerSocket mockServerSocket;
	private boolean isClosed;
	
	public MockServerSocketWrapper(MockServerSocket serverSocket){
		this.mockServerSocket = serverSocket;
		this.isClosed = false;
	}
	
	@Override
	public SocketService accept() throws IOException {
		this.isClosed = false;
		return null;
	}

	@Override
	public void close() throws IOException {
		isClosed = true;
		
	}

	@Override
	public boolean isClosed() throws IOException {
		return isClosed;
	}

}
