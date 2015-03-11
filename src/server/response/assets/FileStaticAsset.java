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
		System.out.println("here"+request.getURI());
		return Utilities.fileExist(request.getURI()) || request.getURI().contentEquals("/test/static");	
	}
	
	@Override
	public Response render(Request request) throws MalformedURLException, UnsupportedEncodingException {
		
		String body = "";
		String absolutePath = Utilities.findServerAbsolutePath();
		String defaultDirectory = "/" + server.Constants.PUBLIC_DIR_IN_USE;

		Routes route = new Routes(request.getURI());
		String routedPath = route.getRoute();
		
		StringBuilder path = new StringBuilder();
		path.append(absolutePath);
		path.append(defaultDirectory);
		path.append(routedPath);
		System.out.println(routedPath);
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
		return new Response(body,body.getBytes(), null);
	}


	
	
}