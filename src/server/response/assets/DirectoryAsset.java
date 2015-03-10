package server.response.assets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import routes.Routes;
import server.Utilities;
import server.request.Request;

public class DirectoryAsset implements Asset {

	@Override
	public boolean canHandle(Request request) {
		return request.getURI().contentEquals("/");	
	}

	@Override
	public String render(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		
		//retrieve Directory Contents as List
		
		
		//render as html
		String body = "";
		String absolutePath = Utilities.findServerAbsolutePath();
		String defaultDirectory = "/" + server.Constants.PUBLIC_DIR_DEFAULT;

		Routes route = new Routes(request.getURI());
		String routedPath = route.getRoute();
		
		StringBuilder path = new StringBuilder();
		path.append(absolutePath);
		path.append(defaultDirectory);
		path.append(routedPath);
		
		if(server.Utilities.fileExist(routedPath)){
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

}
