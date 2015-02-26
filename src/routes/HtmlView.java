package routes;

import java.util.Hashtable;
import java.util.Set;


public class HtmlView {
	private StringBuilder builder;
	private Hashtable<String,String> params;
	public HtmlView(Hashtable<String,String> params){
		builder = new StringBuilder();
		this.params = params;
	}
	
	String build(){
		builder.append("<!doctype html>");
		builder.append("<html>");
		builder.append("<head>");
		builder.append("</head>");
		Set<String> keys = params.keySet();
		for(String key: keys){
			builder.append(key+ ":" + params.get(key));
		}
		builder.append("<body>");
		builder.append("</body>");
		builder.append("</html>");
		
		return builder.toString();
		
		
	}
	
	

}
//<!doctype html><html><head><title>Test at root</title></head><body>Test at root</body></html>