package server.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SocketService {
	
	public void close() throws IOException;

	public OutputStream getOutputStream();

	public InputStream getInputStream();
	
	public boolean isClosed();

}
