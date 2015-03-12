package views;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;


public class HtmlView implements ViewFactory{
	private StringBuilder builder;
	private Hashtable<String,String> params = null;
	private String content = null;
	private List<String> results;
	
	
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

	public HtmlView(List<String> results) {
		builder = new StringBuilder();
		this.results = results;
	}

	public String build(){
		builder.append("<!doctype html>");
		builder.append("<html>");
		builder.append("<head>");
		builder.append("</head>");
		builder.append("<body>");
		
		if(params != null ){
			if(params.containsKey("variable_1")){
				builder.append("variable_1 = " + params.get("variable_1"));
				builder.append("variable_2 = " + params.get("variable_2"));
			}else{
				Set<String> keys = params.keySet();
				for(String key: keys){
					builder.append(key+ ":" + params.get(key) + "<br>");
				}
			}
			
		}
		else if(content == null){
			builder.append("");
		}else if (results != null){
			for(String file : results){
				builder.append(file);
				builder.append("<br>");
			}
			
		}else{
			builder.append(content);
		}
		
		builder.append("</body>");
		builder.append("</html>");
		
		return builder.toString();
	}
	
	public String build(String htmltype){
		builder.append("<!doctype html>");
		builder.append("<html>");
		builder.append("<head>");
		builder.append("</head>");
		builder.append("<body>");
		if(htmltype.contentEquals("directory")){
			builder.append("Directory");
			builder.append("<br>");
			for(String file : results){
				builder.append("<a href=\"");
				builder.append(file + "\">");
				builder.append(file);
				builder.append("</a>");
				builder.append("<br>");
			}
		}else if (htmltype.contentEquals("parameters")){
			if(params.containsKey("variable_1")){
				builder.append("variable_1 = " + params.get("variable_1"));
				builder.append("variable_2 = " + params.get("variable_2"));
			}
		}
		builder.append("</body>");
		builder.append("</html>");
		return builder.toString();
	}

	@Override
	public View create(Content content) {
		// TODO Auto-generated method stub
		return null;
	}

}
