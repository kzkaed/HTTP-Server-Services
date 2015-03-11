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
	
	public static String constructWebRootDirectory(){
		StringBuilder sb = new StringBuilder();
		sb.append("/");
		sb.append(server.Constants.PUBLIC_DIR_IN_USE);
		return sb.toString();
	}
	
	public static boolean fileExist(String uri) {
		String absolutePath = findServerAbsolutePath();
		String webrootDirectory = constructWebRootDirectory();
		
		//Routes route = new Routes(uri);
		//String routedPath = route.getRoute();
		
		StringBuilder sb = new StringBuilder();
		sb.append(absolutePath);
		sb.append(webrootDirectory);
		sb.append(uri);
		
		Path pathCheck = Paths.get(sb.toString());
		return Files.exists(pathCheck, LinkOption.NOFOLLOW_LINKS) && Files.isRegularFile(pathCheck, LinkOption.NOFOLLOW_LINKS);		
	}





}
