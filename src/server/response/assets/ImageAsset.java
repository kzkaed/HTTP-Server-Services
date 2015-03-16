package server.response.assets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import server.helpers.Utility;
import server.request.Request;
import server.response.Response;
import server.response.ResponseCodes;

public class ImageAsset implements Asset {
	
	public ImageAsset(){}

	@Override
	public boolean canHandle(Request request) {
		return isImage(request.getURI());
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		
		Utility.webrootAbsolutePath();
		Path path = Paths.get(Utility.webrootAbsolutePath()  + request.getURI());
		byte[] imageInBytes = null;
		String imageStr = null;
		try {
			imageInBytes = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HashMap<String,String> headers = determineHeaders(getContentType(request.getURI()));
		
		if (imageInBytes != null){
			return new Response(imageStr,imageInBytes,headers, 200, ResponseCodes.getReason("200"));
		}else{
			return new Response(imageStr,imageInBytes,headers, 404, ResponseCodes.getReason("404"));
		}
	}
	
	public HashMap<String,String> determineHeaders(String type) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Server", "Kristin Server");
		headers.put("Content-Type", type);
		return headers;
	}
	
	public String getContentType(String uri){
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		return fileNameMap.getContentTypeFor(uri);
	}
	
	public boolean isImage(String uri){	
		if (getContentType(uri) ==  null){
			return false;
		}
		return (getContentType(uri).contains("image"));	
	}

}
