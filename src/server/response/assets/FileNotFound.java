package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import server.response.*;
import server.Utilities;
import server.request.Request;

public class FileNotFound implements Asset{

	@Override
	public boolean canHandle(Request request) {	
		//if Routes no other handles to handle or file does not exist.
		return !Utilities.fileExist(request.getURI());//only checking files system
	}

	@Override
	public Response render(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		return new Response("","".getBytes(),null);
	}

}
