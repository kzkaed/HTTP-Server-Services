package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;

import routes.Routes;
import server.request.Request;
import server.response.Response;
import server.response.ResponseCodes;

public class TestStatic extends GetFileStaticAsset implements Asset{
	

	@Override
	public boolean canHandle(Request request) {
		return request.getURI().equals("/test/static");
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
	
		
		Routes route = new Routes(request.getURI());
		String body = this.retrieveFileContent(route.getRoute());	
		
		HashMap<String,String> headers = this.determineHeaders("text/html");
		return new Response(body,body.getBytes("UTF8"), headers, 200, ResponseCodes.getReason("200"));
	}
}


