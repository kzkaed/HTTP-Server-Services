package server.response;

import java.util.Hashtable;

public class ResponseCodes {
	
	//Status Code and Reason Phrase

	private static final Hashtable<String,String> HttpResponses = new Hashtable<String, String>() {
	

		
		private static final long serialVersionUID = 1L;

		{
			put("200", "OK");
			put("201", "Created");
			put("202", "Accepted");
			put("404", "Not Found");
			put("500", "Internal Server Error");
			put("502", "Not Implemented");
			
			
		}
		
		
	};
	
	//6.1.1 Status Code and Reason Phrase
	public static String getReason(String responseCode){
		return HttpResponses.get(responseCode);
	}
	
	
	
	
}
