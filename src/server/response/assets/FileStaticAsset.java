package server.response.assets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import routes.Routes;
import server.Utilities;
import server.request.Request;
import server.response.Response;

public class FileStaticAsset implements Asset {

	public FileStaticAsset(){}
	
	@Override
	public boolean canHandle(Request request) {
		System.out.println("can Handle FileStaticAsset" + Utilities.fileExist(request.getURI()) + request.getURI());
		return Utilities.fileExist(request.getURI());	
	}
	
	@Override
	public Response render(Request request) throws MalformedURLException, UnsupportedEncodingException {
		
		String body = "";
		String absolutePath = Utilities.findServerAbsolutePath();
		String defaultDirectory = "/" + server.Constants.PUBLIC_DIR_DEFAULT;

		Routes route = new Routes(request.getURI());
		String routedPath = route.getRoute();
		
		StringBuilder path = new StringBuilder();
		path.append(absolutePath);
		path.append(defaultDirectory);
		path.append(routedPath);
		
		System.out.println(Utilities.fileExist(routedPath));
		System.out.println("routedPath" + routedPath);
		System.out.println("path" + path.toString());
		if (Utilities.fileExist(routedPath)){
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
		
		//Response (String responseBody, byte[] body, HashMap<String,String> headers){
		Response response = new Response(body,body.getBytes(), null);
		System.out.println("file static asset response body" + response.getBody());
		return response;
	}


	
	
}