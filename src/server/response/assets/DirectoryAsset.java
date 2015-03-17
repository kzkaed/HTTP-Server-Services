package server.response.assets;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import server.constants.Method;
import server.request.Request;
import server.response.Response;
import server.response.ResponseCodes;
import views.HtmlView;

public class DirectoryAsset implements Asset{
	
	private static final String CRLF = server.constants.Constant.CRLF;

	public DirectoryAsset(){}

	@Override
	public boolean canHandle(Request request) {
		return request.getURI().contentEquals("/") && request.getMethod().equals(Method.GET);	
	}


	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		
		List<String> results = getDirectoryFileNames();
		String body = retrieveBody(results);
		HashMap<String,String> headers = retrieveHeaders("text/html");
		
		return new Response(body,body.getBytes("UTF8"), headers, 200, ResponseCodes.getReason("200"));
	}
	
	public HashMap<String,String> retrieveHeaders(String type) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Server", "Kristin Server");
		headers.put("Content-Type", type);
		return headers;
	}
	
	public String retrieveBody(List<String> results){
		HtmlView html = new HtmlView(results);
		return html.build("directory");
	}
	
	
	public List<String> getDirectoryFileNames(){
		String directory = server.helpers.Utility.getAbsolutePath("/"+server.Context.PUBLIC_DIR_IN_USE);
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
