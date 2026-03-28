package routes;

import java.util.Map;

public class Routes {

	private static final Map<String, String> ROUTE_MAP = Map.of(
		"/test/static", "/test/index.html",
		"/other", "/test.html"
	);

	private String uri;

	public Routes (String uri) {
		this.uri = uri;
	}

	public String getRoute() {
		return ROUTE_MAP.getOrDefault(uri, uri);
	}

}
