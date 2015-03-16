package server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class Context {

	public static final String HOST = setHost();
	
	public static int PORT_IN_USE; 
	public static String PUBLIC_DIR_IN_USE;
	

	private static String setHost(){
		try {
			return InetAddress.getLocalHost().getHostName().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "Host not set";
		}
	}
	
	public static void setContext(int port, String publicDirectory){
		PORT_IN_USE = port;
		PUBLIC_DIR_IN_USE = publicDirectory;
	}
	
	public final static void setPort(int port){
		PORT_IN_USE = port;
	}
	

	public final static void setPublicDirectory(String publicDirectory) {
		PUBLIC_DIR_IN_USE = publicDirectory;
		
	}
	
	

}