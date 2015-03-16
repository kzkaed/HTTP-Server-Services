package server.helpers;

import java.net.FileNameMap;
import java.net.URI;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import routes.Routes;
import server.constants.Constants;

public class Utility {
	
	public Utility(){}
	
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
	
	public static String webrootAbsolutePath(){
		StringBuilder sb = new StringBuilder();
		sb.append(findServerAbsolutePath());
		sb.append("/");
		sb.append(server.constants.Constants.PUBLIC_DIR_IN_USE);
		return sb.toString();
	}
	
	public static boolean fileExist(String uri) {
		StringBuilder sb = new StringBuilder();
		sb.append(Utility.webrootAbsolutePath());
		sb.append(uri);
		
		Path pathCheck = Paths.get(sb.toString());
		return Files.exists(pathCheck, LinkOption.NOFOLLOW_LINKS) && Files.isRegularFile(pathCheck, LinkOption.NOFOLLOW_LINKS);		
	}

	public static boolean isImage(String uri){
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String type = fileNameMap.getContentTypeFor(uri);
		if (type ==  null){
			return false;
		}
		return (type.contains("image"));	
	}




}
