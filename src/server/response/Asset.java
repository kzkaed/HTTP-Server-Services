package server.response;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public interface Asset {
	
	public String generate(String uri) throws MalformedURLException, UnsupportedEncodingException;
}


