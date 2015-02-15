package server.mocks;

import java.net.ServerSocket;
import java.net.Socket;

import server.socket.ServerSocketService;

public class MockServerSocketWrapper implements ServerSocketService {
	
	private MockServerSocket mockServerSocket;
	private boolean isClosed;
	
	public MockServerSocketWrapper(MockServerSocket serverSocket){
		this.mockServerSocket = serverSocket;
		this.isClosed = false;
	}
	
	@Override
	public Socket accept() throws Exception {
		this.isClosed = false;
		return null;
	}

	@Override
	public void close() throws Exception {
		isClosed = true;
		
	}

	@Override
	public boolean isClosed() throws Exception {
		return isClosed;
	}

}
