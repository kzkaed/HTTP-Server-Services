package server.response;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import routes.AssetManager;
import server.request.Request;
import server.response.assets.Asset;

public class ResponseBuilder {

	
	private final String CRLF = server.constants.Constant.CRLF;
	
	private final String STATUS_404 = server.constants.Constant.STATUS_404;
	private final String STATUS_502 = server.constants.Constant.STATUS_502;
	
	private final List<String> METHODS_IMPLEMENTED = Arrays.asList("GET","POST","PUT","HEAD","OPTIONS"); 
	
	private Request request;
	
	public ResponseBuilder(Request request) {
		this.request = request;

	}

	public Response buildResponse(AssetManager manager) throws IOException {
		Asset asset = manager.getAsset(request);
		System.out.println("METHOD: " + request.getMethod());
		System.out.println("REQUEST: " + request.getURI());
		System.out.println("ASSET: "+ asset.getClass());
		return asset.execute(request);
	}
	
	public boolean isMethodImplemented(){
		return  METHODS_IMPLEMENTED.contains(request.getMethod());
	}
	
	
	

}
