package server.socket;

import java.io.IOException;



public interface ServerSocketService {
	
	public Object accept() throws IOException;
	public void close() throws IOException;
	public boolean isClosed() throws IOException;

}

