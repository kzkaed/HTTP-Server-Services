package routes;

import java.util.Hashtable;
import java.util.Set;


public class HtmlView {
	private StringBuilder builder;
	private Hashtable<String,String> params;
	private String content;
	
	
	public HtmlView(Hashtable<String,String> params){
		builder = new StringBuilder();
		this.params = params;
		
	}
	
	public HtmlView(){
		builder = new StringBuilder();
	}
	
	public HtmlView(String content){
		builder = new StringBuilder();
		this.content = content;	
	}
	
	public String build(){
		builder.append("<!doctype html>");
		builder.append("<html>");
		builder.append("<head>");
		builder.append("</head>");
		builder.append("<body>");
		
		if(params != null ){
			Set<String> keys = params.keySet();
			for(String key: keys){
				builder.append(key+ ":" + params.get(key));
				
			}
			
		}else{
			builder.append(content);
		}
		
		builder.append("</body>");
		builder.append("</html>");
		
		return builder.toString();
	}

}
