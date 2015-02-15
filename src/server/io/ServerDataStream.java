package server.io;

import java.io.BufferedReader;
import java.io.DataOutputStream;

public interface ServerDataStream {
	DataOutputStream out = null;
	BufferedReader in = null;
	
	public String read();
	public void write(String response);
	
	
}
