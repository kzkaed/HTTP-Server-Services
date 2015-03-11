package server.response.assets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import routes.Routes;
import server.Utilities;
import server.request.Request;
import server.response.Response;
import views.HtmlView;

public class DirectoryAsset implements Asset {
	
	public DirectoryAsset(){}

	@Override
	public boolean canHandle(Request request) {
		return request.getURI().contentEquals("/");	
	}

	@Override
	public Response render(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		
		String directory = server.Utilities.getAbsolutePath("/"+server.Constants.PUBLIC_DIR_IN_USE);
		File[] files = new File(directory).listFiles();
		List<String> results = new ArrayList<String>();
		for (File file : files) {
		    if (file.isFile()) {
		        results.add(file.getName()); 
		    }
		}

		HtmlView html = new HtmlView(results);
		String body = html.build();
			
		return new Response(body,body.getBytes() , null);
	
	}

}
