package server.response;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AssetFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReturnDynamicAssetFalse() {
		String uri = "/test/static";
		AssetFactory assetFactory = new AssetFactory(uri);
		Asset asset = assetFactory.getAsset();
		Asset expectedAsset = new FileStaticAsset();
		assertEquals(expectedAsset.getClass(), asset.getClass());
	}

	
	@Test
	public void testReturnDynamicAssetonStaticAssetTrue() {
		String uri = "/test/dynamic";
		AssetFactory assetFactory = new AssetFactory(uri);
		Asset asset = assetFactory.getAsset();
		Asset expectedAsset = new DynamicAsset();
		assertEquals(expectedAsset.getClass(), asset.getClass());
	}
}
