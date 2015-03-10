package server.response.assets;


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Set;

import routes.HtmlView;
import server.request.ParametersParser;
import server.request.ParametersParserURL;
import server.request.Request;
import server.response.Response;

public class DynamicAsset implements Asset{
		
	private Hashtable<String,String> parameters;
	private HtmlView html;

	public DynamicAsset ()  {}
	
	public Response render(Request request) throws MalformedURLException, UnsupportedEncodingException{
		if (request.getParmeters() != null && !request.getParmeters().isEmpty()){
			parameters = request.getParmeters();
			html = new HtmlView(parameters);
		}else if(request.getURI().contentEquals("/")){
			html = new HtmlView("<!doctype html><html><head>"
					+ "<title>HTTP-Server-Service Test HTML</title>"
					+ "</head><body>Directory Listing<br>"
					+ "<a href=\"/response\">response</a><br>"
					+ "<a href=\"/test\">test</a><br>"
					+ "<a href=\"favicon.ico\">favicon.ico</a><br>"
					+ "<a href=\"file1\">file1</a><br>"
					+ "<a href=\"file2\">file2</a><br>"
					+ "<a href=\"image.jpeg\">image.jpeg</a><br>"
					+ "<a href=\"image.png\">image.png</a><br>"
					+ "<a href=\"image.gif\">image.gif</a><br>"
					+ "<a href=\"index.html\">index.html</a><br>"
					+ "<a href=\"information.html\">information.html</a><br>"
					+ "<a href=\"partial_content.txt\">partial-content.txt</a><br>"
					+ "<a href=\"patch_content.txt\">patch-content.txt</a><br>"
					+ "<a href=\"text-file.txt\">text-file.txt</a><br></body></html>");
		}else if(request.getURI().contentEquals("/test/dynamic")){
			html = new HtmlView("test dynamic");
		}else{
			html = new HtmlView(request.getURI());
		}
		
		
		
		//Response (String responseBody, byte[] body, HashMap<String,String> headers){
		Response response = new Response(html.build(),html.build().getBytes() , null);
						
		return response;
		
	}

	@Override
	public boolean canHandle(Request request) {
			return
				request.getURI().contentEquals("/") || 
				request.getURI().contentEquals("/test/dynamic") ||
				request.getURI().contains("showParams"); 
				
			
	}



}
