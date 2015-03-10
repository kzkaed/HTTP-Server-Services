package routes;

import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.Test;

import server.request.Request;
import server.response.assets.Asset;

public class AssetManagerTest {
	
	
	@Test
	public void registersASingleAsset(){
		AssetManager manager = new AssetManager();
		Asset asset = new MockAsset();
		manager.register(asset);
		assertEquals(manager.getAssets().size(), 1);		
	}
	
	@Test
	public void executeAssets(){
		AssetManager manager = new AssetManager();		
		Request request = new Request("method", "uri", "protocol", null, "requestLine", null, null);
		Asset asset1 = new MockAsset("hi Kristin");
		Asset asset2 = new MockAsset("hi");
		Asset asset3 = new MockAsset("uri");
		
		manager.register(asset1);
		manager.register(asset2);
		manager.register(asset3);
		
		assertEquals(manager.getAsset(request), asset3);
	}

}
