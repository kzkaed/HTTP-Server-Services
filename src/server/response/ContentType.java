package server.response;

import java.net.FileNameMap;
import java.net.URLConnection;

public class ContentType {
	public static final String TEXT_HTML = "text/html";
	public static final String TEXT_PLAIN = "text/plain";
	public static final String TEXT_CSS = "text/css";
	public static final String TEXT_JS = "application/javascript";
	public static final String APP_JSON = "application/json";
	public static final String APP_OCTET = "application/octet-stream";

	public static String forPath(String path) {
		if (path == null) return APP_OCTET;

		// URLConnection doesn't know .css or .js — handle them explicitly
		if (path.endsWith(".css")) return TEXT_CSS;
		if (path.endsWith(".js")) return TEXT_JS;
		if (path.endsWith(".json")) return APP_JSON;
		if (path.endsWith(".txt")) return TEXT_PLAIN;
		if (path.endsWith(".html") || path.endsWith(".htm")) return TEXT_HTML;

		// Fall back to JDK's built-in MIME map (handles images, etc.)
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String type = fileNameMap.getContentTypeFor(path);

		return (type != null) ? type : APP_OCTET;
	}
}
