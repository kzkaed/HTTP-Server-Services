package server.response.assets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import log.SystemLogger;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.HashMap;
import routes.Routes;
import server.constants.Method;
import server.helpers.FileLocator;
import server.request.Request;
import server.response.ContentType;
import server.response.Response;
import server.response.ResponseCodes;

public class StaticAsset implements Asset {

	private final String publicDir;

	public StaticAsset(String publicDir) {
		this.publicDir = publicDir;
	}

	@Override
	public boolean canHandle(Request request) {
		return FileLocator.fileExist(request.getURI(), publicDir) && !isImage(request.getURI()) && request.getMethod().equals(Method.GET);
	}
	
	@Override
	public Response execute(Request request) throws MalformedURLException, UnsupportedEncodingException {

		Routes route = new Routes(request.getURI());
		
		String body = retrieveFileContent(route.getRoute());	
		
		HashMap<String,String> headers = determineHeaders(ContentType.forPath(route.getRoute()));
		return new Response(body,body.getBytes("UTF8"), headers, 200, ResponseCodes.getReason("200"));
	}

	
	public String retrieveFileContent(String routedPath){
		String body = "";
		String absolutePath = FileLocator.findServerAbsolutePath();
		String defaultDirectory = "/" + publicDir;

		StringBuilder path = new StringBuilder();
		path.append(absolutePath);
		path.append(defaultDirectory);
		path.append(routedPath);

		if (FileLocator.fileExist(routedPath, publicDir)){
			try {
				BufferedReader in = new BufferedReader(new FileReader(path.toString()));
				String str;
				while ((str = in.readLine()) != null) {
					body += str;	
				}
				in.close();
			} catch (IOException e) {
				SystemLogger.getInstance().error("Failed to read file " + path + ": " + e.getMessage());
			}
		}
		
		
		return body;
	}

	public HashMap<String,String> determineHeaders(String type) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Server", "Kristin Server");
		headers.put("Content-Type", type);
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