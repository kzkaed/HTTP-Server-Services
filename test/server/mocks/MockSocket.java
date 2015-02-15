package server.mocks;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import server.socket.SocketService;

public class MockSocket extends Socket implements SocketService{
	
	public static final int UNBOUND = 0;
	public static final int BOUND = 10;
	public static final int CONNECTED = 20;
	public static final int DISCONNECTED = 30;
	private int state; //ENUMS?
	
	private InputStream input;
	private OutputStream output;
	
	private int port;
	private String host;
	private InetAddress address;
	
	private boolean inputShutdown;
	private boolean outputShutdown;
	
	
	private boolean closed = false;
	
	public MockSocket(){
		state = UNBOUND;
	}
	
	public MockSocket(String host, int port) throws IOException{
		super(host,port);
		
	}
	public MockSocket(String host, int port, byte[] in){
		this.input = new ByteArrayInputStream(in);
		this.output = new ByteArrayOutputStream();
	}
	
	@Override
	public void close(){
		this.closed = true;
	}
	
	@Override
	public boolean isClosed() {
		return this.closed;
	}

	@Override
	public OutputStream getOutputStream() {
		return output;
	}

	@Override
	public InputStream getInputStream() {
		return input;
	}
	
	

}
