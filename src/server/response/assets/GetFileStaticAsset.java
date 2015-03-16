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
import server.constants.Methods;
import server.helpers.Utility;
import server.request.Request;
import server.response.Response;
import server.response.ResponseCodes;

public class GetFileStaticAsset implements Asset {

	private static final String CRLF = server.constants.Constant.CRLF;

	public GetFileStaticAsset(){};
	
	@Override
	public boolean canHandle(Request request) {
		return Utility.fileExist(request.getURI()) && !isImage(request.getURI()) && request.getMethod().equals(Methods.GET); 
	}
	
	@Override
	public Response execute(Request request) throws MalformedURLException, UnsupportedEncodingException {

		Routes route = new Routes(request.getURI());
		String body = retrieveFileContent(route.getRoute());	
		
		HashMap<String,String> headers = determineHeaders("text/html");
		return new Response(body,body.getBytes("UTF8"), headers, 200, ResponseCodes.getReason("200"));
	}

	public String retrieveFileContent(String routedPath){
		String body = "";
		String absolutePath = Utility.findServerAbsolutePath();
		String defaultDirectory = "/" + server.Context.PUBLIC_DIR_IN_USE;

		StringBuilder path = new StringBuilder();
		path.append(absolutePath);
		path.append(defaultDirectory);
		path.append(routedPath);
		
		if (Utility.fileExist(routedPath)){
			try {
				BufferedReader in = new BufferedReader(new FileReader(path.toString()));
				String str;
				while ((str = in.readLine()) != null) {
					body += str;	
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
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