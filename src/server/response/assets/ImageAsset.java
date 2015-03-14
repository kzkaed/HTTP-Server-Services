package server.response.assets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import server.Utilities;
import server.request.Request;
import server.response.Response;

public class ImageAsset extends Get {
	
	private static final String CRLF = server.Constants.CRLF;

	public ImageAsset(){}

	@Override
	public boolean canHandle(Request request) {
		System.out.println("is image");
		return isImage(request.getURI());
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		
		Utilities.webrootAbsolutePath();
		Path path = Paths.get(Utilities.webrootAbsolutePath()  + request.getURI());
		byte[] imageInBytes = null;
		String imageStr = null;
		try {
			imageInBytes = Files.readAllBytes(path);
			imageStr = Base64.encode(imageInBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Response(imageStr,imageInBytes,null,buildResponseHeaders());
	}
	
	public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
						+ "Accept-Ranges: bytes" + CRLF 
						+ "Content-Type: image/jpeg" + CRLF;
						//+ "Connection: Close" + CRLF;
	
		return headers;
	}
	
	public boolean isImage(String uri){
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String type = fileNameMap.getContentTypeFor(uri);
		if (type ==  null){
			return false;
		}
		return (type.contains("image"));	
	}

}
