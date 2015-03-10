package routes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import server.request.Request;
import server.response.assets.Asset;
import server.response.assets.FileStaticAsset;

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
		System.out.println("get Asset of" + request.getURI());
		Iterator<Asset> iterator = assets.iterator();
		while(iterator.hasNext()) {
			Asset asset = iterator.next();
			if(asset.canHandle(request)){
				System.out.println("asset " + asset.getClass());
				return asset;	
			}
				
			
		}
		System.out.println("file static asset with " + request.getURI());
		return new FileStaticAsset();		
	}

}
