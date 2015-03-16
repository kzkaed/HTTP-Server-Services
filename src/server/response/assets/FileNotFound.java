package server.response.assets;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;

import server.helpers.Utility;
import server.response.*;
import server.request.Request;

public class FileNotFound implements Asset{
	
	public FileNotFound() {}
	
	@Override
	public boolean canHandle(Request request) {	
		return !Utility.fileExist(request.getURI());
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		String responseCodeReason = ResponseCodes.getReason("404");
		return new Response(responseCodeReason,responseCodeReason.getBytes(),determineHeaders(ContentType.TEXT_HTML), 404, responseCodeReason);
	}
	
	public HashMap<String,String> determineHeaders(String type) {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Server", "Kristin Server");
		headers.put("Content-Type", type);
		return headers;
	}
}
