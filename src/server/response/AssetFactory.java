package server.response;

public class AssetFactory {
	public boolean staticAssetExist = false;
	private String uri;
	
	AssetFactory(String uri){
		this.uri = uri;
	}
	
	
	public Asset getAsset(){
		
		if(doesStaticAssetExist(uri)){
			return new FileStaticAsset();
		}
		return new DynamicAsset();		
	}
	
	
	public boolean doesStaticAssetExist(String filename){
		return server.Utilities.doesFileExist(filename);
	}

}
