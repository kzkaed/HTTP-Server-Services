package server;

import java.io.FileOutputStream;
import java.io.IOException;
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
	
	public static String findServerAbsolutePath() {
		Path path = Paths.get("");
		String absolutePath = path.toAbsolutePath().toString();
		return absolutePath;
	}
	
	public static URI findServerPathURI() {
		Path path = Paths.get("");
		URI pathAsURI = path.toUri();
		return pathAsURI;
	}
	
	public static String getAbsolutePath(String relativePath){
		String absolutePath = findServerAbsolutePath();
		StringBuilder sb = new StringBuilder();
		sb.append(absolutePath);
		sb.append(relativePath);
		return sb.toString();
	}
	
	public static boolean doesFileExist(String filename) {
		String absolutePath = findServerAbsolutePath();
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
