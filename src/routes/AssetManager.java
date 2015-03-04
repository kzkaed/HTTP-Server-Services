package routes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import server.request.Request;
import server.response.Asset;
import server.response.FileStaticAsset;

public class AssetManager {
	
	private ArrayList<Asset> assets;

	public AssetManager(){
		assets = new ArrayList<Asset>();
	}

	public void register(Asset asset) {
		assets.add(asset);
	}

	public List<Asset> getAssets() {
		return assets;
	}

	public Asset getAsset(Request request) {
		Iterator<Asset> iterator = assets.iterator();
		while(iterator.hasNext()) {
			Asset asset = iterator.next();
			if(asset.canHandle(request))
				return asset;			
		}
		return new FileStaticAsset();
			
	}

}
