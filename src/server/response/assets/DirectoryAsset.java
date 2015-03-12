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
	
	private static final String CRLF = server.Constants.CRLF;

	public DirectoryAsset(){}

	@Override
	public boolean canHandle(Request request) {
		return request.getURI().contentEquals("/");	
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		
		//FileSystem: Content Retrieval
		String directory = server.Utilities.getAbsolutePath("/"+server.Constants.PUBLIC_DIR_IN_USE);
		File[] files = new File(directory).listFiles();
		List<String> results = new ArrayList<String>();
		for (File file : files) {
		    if (file.isFile()) {
		        results.add(file.getName()); 
		    }
		}

		//Render/Create View
		HtmlView html = new HtmlView(results);
		String body = html.build("directory");
		
		//BuildResponse
		byte[] utf8Bytes = body.getBytes("UTF8");
		String roundTrip = new String(utf8Bytes, "UTF8");
		System.out.println(utf8Bytes);
		System.out.println(roundTrip);
		return new Response(body,utf8Bytes, null, buildResponseHeaders());
	
	}
	
	public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
						+ "Accept-Ranges: bytes" + CRLF 
						+ "Content-Type: text/html" + CRLF;
						//+ "Connection: Close" + CRLF;
	
		return headers;
	}

}
