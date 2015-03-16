package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import server.request.Request;
import server.response.Response;
import server.response.ResponseCodes;
import views.HtmlView;

public class TestDynamic extends DynamicAsset implements Asset {
	
	public TestDynamic(){
		super();
	}

	@Override
	public boolean canHandle(Request request) {
		return request.getURI().equals("/test/dynamic");
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		HtmlView html = render(request);	
		return new Response(html.build(),html.build().getBytes() , determineHeaders("text/html"), 200, ResponseCodes.getReason("200"));
	}

	
	
}
