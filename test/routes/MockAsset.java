package routes;

import server.request.Request;
import server.response.assets.Asset;
import server.response.*;

public class MockAsset implements Asset{
	
	private String uriMatcher;

	public MockAsset(String uriMatcher){
		this.uriMatcher = uriMatcher; 
	}
	
	public MockAsset() {}

	@Override
	public Response render(Request request){
		return null;
	}

	@Override
	public boolean canHandle(Request request) {
		return uriMatcher == request.getURI();
	}

}
