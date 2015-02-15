package server.socket;

import java.net.Socket;

public interface ServerSocketService {
	
	public Object accept() throws Exception;
	public void close() throws Exception;
	public boolean isClosed() throws Exception;

}

