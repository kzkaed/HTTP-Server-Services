package com.scutigera.color;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import routes.AssetManager;

@DisplayName("Application")
public class ApplicationTest {

	@Nested
	@DisplayName("when setting context")
	class WhenSettingContext {

		@Test
		@DisplayName("updates the port and public directory in the server context")
		void updates_the_port_and_public_directory_in_the_server_context() {
			Application.setContext(1234, "scallywag");
			assertEquals(server.Context.PORT_IN_USE, 1234, "port should be updated to the provided value");
			assertEquals(server.Context.PUBLIC_DIR_IN_USE, "scallywag", "public directory should be updated to the provided value");
		}
	}

	@Nested
	@DisplayName("when registering application assets")
	class WhenRegisteringApplicationAssets {

		@Test
		@DisplayName("returns a manager with registered assets")
		void returns_a_manager_with_registered_assets() {
			AssetManager manager = Application.registerApplicationAssets(new AssetManager());
			assertNotNull(manager.getAssets(), "registered assets should not be null");
		}
	}
}
