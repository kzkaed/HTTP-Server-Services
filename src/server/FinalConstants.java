package server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class FinalConstants {

	//read once into local variable
	public static final String PORT_DEFAULT = "5000";
	public static final String PUBLIC_DIR_DEFAULT = "public";
	
	public static final String VERSION_PROTOCOL = "HTTP/1.1";
	public static final String CRLF = "\r\n";
	public static final String SPACE = "";
	public static final String SPACE_E = "\\s";
	public static final String HEADERS_END = CRLF + CRLF;
	public static final String COLON = ": ";
	
	public static final String DELIMITER_SPACE = "[ ]+";
	public static final String DELIMITER_QUERY = "\\?";
	public static final String DELIMITER_EQUAL = "=";
	public static final String DELIMITER_AMP = "&";
	
	public static final String STATUS_200 = "HTTP/1.1 200 OK" + CRLF;
	public static final String STATUS_404 = "HTTP/1.1 404 Not Found" + CRLF;
	public static final String STATUS_500 = "HTTP/1.1 500 Internal Server Error" + CRLF;
	public static final String STATUS_502 = "HTTP/1.1 502 Not Implemented" + CRLF;
		
	public static final String TEST_ROUTE = "/test/index";
	public static final String DEFAULT_INDEX = "/index.html";
	public static final String TEST_FILE = "/test.html";
	
	public static final String HOST = setHost();
	
		

	private static String setHost(){
		try {
			return InetAddress.getLocalHost().getHostName().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "Host not set";
		}
	}
	
	
	
	

}