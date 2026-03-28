package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import server.request.Request;
import server.response.Response;
import server.response.ResponseCodes;
import views.ViewFactory;


public class DynamicPathExt extends DynamicAsset implements Asset {
	
	public DynamicPathExt(ViewFactory viewFactory){
		super(viewFactory);
	}

	@Override
	public boolean canHandle(Request request) {
		return request.getURI().equals("/test/dynamic");
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		this.body = retrieveBody(request);
		return new Response(this.body,this.body.getBytes() , retrieveHeaders("text/html"), 200, ResponseCodes.getReason("200"));
	}

	
	
}
