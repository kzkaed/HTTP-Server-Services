package server.response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import routes.Routes;

public class FileStaticAsset {

	public FileStaticAsset(){}
	
	
	public String findPathAbsolute(String path) {
		Path currentRelativePath = Paths.get(path);
		String absolutePath = currentRelativePath.toAbsolutePath().toString();
		return absolutePath;
	}
	
	public URI findPathURI(String path) {
		Path currentRelativePath = Paths.get(path);
		URI pathAsURI = currentRelativePath.toUri();
		return pathAsURI;
	}

	public String getResponseBody(String uri) {
		String body = "";
		String absolutePath = findPathAbsolute("");
		String defaultDirectory = "/" + server.FinalConstants.PUBLIC_DIR_DEFAULT;

		Routes route = new Routes();
		String routedPath = route.getRoute(uri);
		System.out.println(routedPath);

		String path = absolutePath + defaultDirectory + routedPath;

		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String str;
			while ((str = in.readLine()) != null) {
				body += str;
			}
			in.close();
		} catch (IOException e) {

		}

		return body;
	}
	
}