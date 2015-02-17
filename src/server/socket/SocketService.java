package server.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SocketService {
	
	public void close() throws IOException;
	public OutputStream getOutputStream() throws IOException;
	public InputStream getInputStream() throws IOException;
	public boolean isClosed();

}
