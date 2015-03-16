package server.response;

import java.io.IOException;
import routes.AssetManager;
import server.request.Request;
import server.response.assets.Asset;

public class ResponseBuilder {

	private Request request;
	
	public ResponseBuilder(Request request) {
		this.request = request;
	}

	public Response buildResponse(AssetManager manager) throws IOException {
		Asset asset = manager.getAsset(request);
		return asset.execute(request);
	}

}
