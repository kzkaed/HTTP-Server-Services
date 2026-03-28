package server.helpers;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLocator {

	public static String findServerAbsolutePath() {
		return Paths.get("").toAbsolutePath().toString();
	}

	public static URI findServerPathURI() {
		return Paths.get("").toUri();
	}

	public static String getAbsolutePath(String relativePath) {
		return findServerAbsolutePath() + relativePath;
	}

	public static String webrootAbsolutePath(String publicDir) {
		return findServerAbsolutePath() + "/" + publicDir;
	}

	public static boolean fileExist(String uri, String publicDir) {
		Path path = Paths.get(webrootAbsolutePath(publicDir) + uri);
		return Files.exists(path, LinkOption.NOFOLLOW_LINKS) && Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS);
	}

}
