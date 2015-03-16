package com.scutigera.color;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import routes.AssetManager;

public class ApplicationTest {
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testSetContext(){
		Application.setContext(1234, "scallywag");
		assertEquals(server.constants.Context.PORT_IN_USE, 1234);
		assertEquals(server.constants.Context.PUBLIC_DIR_IN_USE, "scallywag");
	}

	@Test
	public void testApplicationReturnAMangerOfRegisteredAssets() {
		AssetManager manager = Application.registerApplicationAssets(new AssetManager());
		assertNotNull(manager.getAssets());	
	}

}
