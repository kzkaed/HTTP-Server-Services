package routes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import server.request.Request;
import server.response.assets.Asset;

@DisplayName("AssetManager")
public class AssetManagerTest {

	@Nested
	@DisplayName("when registering assets")
	class WhenRegisteringAssets {

		@Test
		@DisplayName("stores a single registered asset")
		void stores_a_single_registered_asset() {
			AssetManager manager = new AssetManager();
			Asset asset = new MockAsset();
			manager.register(asset);
			assertEquals(manager.getAssets().size(), 1, "manager should contain exactly one asset after registration");
		}
	}

	@Nested
	@DisplayName("when looking up an asset for a request")
	class WhenLookingUpAnAsset {

		@Test
		@DisplayName("returns the asset whose URI matches the request")
		void returns_the_asset_whose_uri_matches_the_request() {
			AssetManager manager = new AssetManager();
			Request request = new Request("method", "uri", "protocol", null, "requestLine", null, null);
			Asset asset1 = new MockAsset("hi Kristin");
			Asset asset2 = new MockAsset("hi");
			Asset asset3 = new MockAsset("uri");

			manager.register(asset1);
			manager.register(asset2);
			manager.register(asset3);

			assertEquals(manager.getAsset(request), asset3, "should return the asset matching the request URI");
		}
	}
}
