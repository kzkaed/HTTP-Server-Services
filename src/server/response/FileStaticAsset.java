package server.response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import routes.Routes;
import server.Utilities;
import server.request.Request;

public class FileStaticAsset implements Asset {

	public FileStaticAsset(){}
	
	
	public String render(Request request) throws MalformedURLException, UnsupportedEncodingException {
		String body = "";
		String absolutePath = Utilities.findPathAbsolute("");
		String defaultDirectory = "/" + server.Constants.PUBLIC_DIR_DEFAULT;

		Routes route = new Routes(request.getURI());
		String routedPath = route.getRoute();
		
		String path = absolutePath + defaultDirectory + routedPath;

		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String str;
			while ((str = in.readLine()) != null) {
				body += str;
			}
			in.close();
		} catch (IOException e) {

		}

		return body;
	}


	@Override
	public boolean canHandle(Request request) {
		if(request.getURI().contains("test/static")){
			return true;
		}else if (request.getURI().contains("/static")){
			return true;
		}
		
		return false;		
	}
	
}