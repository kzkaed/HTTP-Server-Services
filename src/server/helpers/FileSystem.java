package server.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileSystem {
	
	
	String fileContent(String routedPath){
	String body = "";
	String absolutePath = Utility.findServerAbsolutePath();
	String defaultDirectory = "/" + server.Context.PUBLIC_DIR_IN_USE;

	StringBuilder path = new StringBuilder();
	path.append(absolutePath);
	path.append(defaultDirectory);
	path.append(routedPath);
	
	if (Utility.fileExist(routedPath)){
		try {
			BufferedReader in = new BufferedReader(new FileReader(path.toString()));
			String str;
			while ((str = in.readLine()) != null) {
				body += str;	
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	return body;
	}
}
