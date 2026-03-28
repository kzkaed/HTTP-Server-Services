package routes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Routes")
public class RoutesTest {

	@Nested
	@DisplayName("when resolving a route")
	class WhenResolvingARoute {

		@Test
		@DisplayName("maps a static path to its index.html document")
		void maps_a_static_path_to_its_index_html_document() throws MalformedURLException, UnsupportedEncodingException {
			Routes route = new Routes("/test/static");
			String documentPath = route.getRoute();
			assertEquals(documentPath,"/test/index.html", "static route should resolve to index.html");
		}
	}
}
