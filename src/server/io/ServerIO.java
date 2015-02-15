package server.io;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerIO implements ServerConnection, ServerDataStream {
	private ServerSocket serverSocket;
	private String request;
	Socket socket;
	BufferedReader in;
	DataOutputStream out;
	
	
	public ServerIO(int port) throws IOException{
		serverSocket = new ServerSocket(port);
		socket = new Socket();
		out = new DataOutputStream(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void accept(){
		try{
			socket = serverSocket.accept();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	public void close(){
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public String read() {
		try {
			in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
			request = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return request;
	}
	
	
	public void write(String response){
			try {
				out.write(response.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}


	
}

