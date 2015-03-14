package server.response.assets;


import java.io.File;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import server.request.Request;
import server.response.Response;
import views.HtmlView;

public class DirectoryAsset extends Get{
	
	private static final String CRLF = server.Constants.CRLF;

	public DirectoryAsset(){
		super();
	}

	@Override
	public boolean canHandle(Request request) {
		return request.getURI().contentEquals("/");	
	}


	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		
		List<String> results = getDirectoryFileNames();
		String body = render(results);

		return new Response(body,body.getBytes("UTF8"), null, buildResponseHeaders());
	}
	
	public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
						+ "Accept-Ranges: bytes" + CRLF 
						+ "Content-Type: text/html" + CRLF;
						//+ "Connection: Close" + CRLF;
		return headers;
	}
	
	public String render(List<String> results){
		HtmlView html = new HtmlView(results);
		return html.build("directory");
	}
	
	
	public List<String> getDirectoryFileNames(){
		String directory = server.Utilities.getAbsolutePath("/"+server.Constants.PUBLIC_DIR_IN_USE);
		File[] files = new File(directory).listFiles();
		List<String> results = new ArrayList<String>();
		for (File file : files) {
		    if (file.isFile()) {
		        results.add(file.getName()); 
		    }
		}
		return results;
	}

}
