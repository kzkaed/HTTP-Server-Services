package server;

public class Utilities {
	
	public Utilities(){}
	
	public String[] retreiveTokens(String request, String delimiters) {
		String[] tokens = request.split(delimiters);
		return tokens;
	}

}
