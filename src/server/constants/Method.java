package server.constants;

import java.util.Arrays;
import java.util.List;



public class Method {

	public final static String GET = "GET";
	public final static String POST = "POST";
	public final static String PUT = "PUT";
	public final static String HEAD = "HEAD";
	public final static String OPTIONS = "OPTIONS";
	
	private final static List<String> METHODS_IMPLEMENTED = Arrays.asList("GET","POST","PUT","HEAD","OPTIONS"); 
	
	public static boolean isMethodImplemented(String method){
		return  METHODS_IMPLEMENTED.contains(method);
	}

}