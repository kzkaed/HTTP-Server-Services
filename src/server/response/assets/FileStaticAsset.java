package server.response.assets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import routes.Routes;
import server.Utilities;
import server.request.Request;
import server.response.Response;

public class FileStaticAsset implements Asset {

	private static final String CRLF = server.Constants.CRLF;


	public FileStaticAsset(){}
	
	@Override
	public boolean canHandle(Request request) {
		String uri = request.getURI();
		return Utilities.fileExist(uri) && !isImage(uri);	
	}
	
	@Override
	public Response execute(Request request) throws MalformedURLException, UnsupportedEncodingException {
		
		String body = "";
		String absolutePath = Utilities.findServerAbsolutePath();
		String defaultDirectory = "/" + server.Constants.PUBLIC_DIR_IN_USE;

		Routes route = new Routes(request.getURI());
		String routedPath = route.getRoute();
		
		StringBuilder path = new StringBuilder();
		path.append(absolutePath);
		path.append(defaultDirectory);
		path.append(routedPath);
		
		if (Utilities.fileExist(routedPath)){
			try {
				BufferedReader in = new BufferedReader(new FileReader(path.toString()));
				String str;
				while ((str = in.readLine()) != null) {
					body += str;//read as byte[]	
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		byte[] utf8Bytes = body.getBytes("UTF8");
		return new Response(body,utf8Bytes, null, buildResponseHeaders());
	}


	public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
						+ "Accept-Ranges: bytes" + CRLF 
						+ "Content-Type: text/html" + CRLF;
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