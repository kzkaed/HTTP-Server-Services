package server.socket;



public interface ServerSocketService {
	
	public Object accept() throws Exception;
	public void close() throws Exception;
	public boolean isClosed() throws Exception;

}

