package com.scutigera.color;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

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

	@Nested
	@DisplayName("when checking class design")
	class WhenCheckingClassDesign {

		@Test
		@DisplayName("has a private constructor to prevent instantiation")
		void has_a_private_constructor_to_prevent_instantiation() throws Exception {
			Constructor<Application> constructor = Application.class.getDeclaredConstructor();
			assertTrue(Modifier.isPrivate(constructor.getModifiers()),
					"constructor should be private");
			constructor.setAccessible(true);
			assertNotNull(constructor.newInstance(),
					"private constructor should still create an instance via reflection");
		}
	}
}
