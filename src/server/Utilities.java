package server;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import routes.Routes;

public class Utilities {
	
	public Utilities(){}
	
	public static String[] retreiveTokens(String request, String delimiters) {
		String[] tokens = request.split(delimiters);
		return tokens;
	}
	
	public static String findPathAbsolute(String path) {
		Path currentRelativePath = Paths.get(path);
		String absolutePath = currentRelativePath.toAbsolutePath().toString();
		return absolutePath;
	}
	
	public static URI findPathURI(String path) {
		Path currentRelativePath = Paths.get(path);
		URI pathAsURI = currentRelativePath.toUri();
		return pathAsURI;
	}
	
	public static boolean doesFileExist(String filename) {
		String absolutePath = findPathAbsolute("");
		String defaultDirectory = "/" + server.Constants.PUBLIC_DIR_DEFAULT;

		Routes route = new Routes(filename);
		String routedPath = route.getRoute();

		String path = absolutePath + defaultDirectory + routedPath;

		Path pathCheck = Paths.get(path);
		if (Files.exists(pathCheck, LinkOption.NOFOLLOW_LINKS)){
			return true;
		}
		return false;
	}


}
