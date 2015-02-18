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
	
	String statusLine = "HTTP/1.1 200 OK\r\n" ;
	String mockOutput = statusLine 
	+"Date: Wed, 18 Feb 2015 06:29:56 CST"
	+"Server: Kristin"
	+"Last-Modified: Wed, 18 Feb 2015 06:29:56 CST"
	+"ETag: 34aa387-d-1568eb00"
	+"Accept-Ranges: bytes"
	+"Content-Length: 59"
	+"Vary: Accept-Encoding"
	+"Content-Type: text/html"
	+"\r\n"
	+"<!doctype html><html><head></head><body>hello</body></html>";
	
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
		state = BOUND;
	}
	public MockSocket(String host, int port, byte[] in) throws IOException{
		this.input = new ByteArrayInputStream(in);
		this.output = new ByteArrayOutputStream();
		state = BOUND;
	}
	
	@Override
	public void close(){
		this.closed = true;
		state = UNBOUND;
	}
	
	@Override
	public boolean isClosed() {
		return this.closed;
	}

	@Override
	public OutputStream getOutputStream() {
		return output;
	}
	
	public String getOutputMock() {
		return statusLine;
	}

	@Override
	public InputStream getInputStream() {
		return input;
	}
	
	@Override
	public boolean isBound(){
		if (state == BOUND){
			return true;
		}else{
			return false;
		}
		 
	}

}
