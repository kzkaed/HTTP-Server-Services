package com.scutigera.color;

public class ColorHtml {
	private StringBuilder builder;
	private String backgroundColor;
	private String[] colors = {"aqua","green","red","yellow","black","gray","white"};
	
	public ColorHtml(){
		builder = new StringBuilder();		
	}

	
	public ColorHtml(String backgroundColor){
		builder = new StringBuilder();
		this.backgroundColor = backgroundColor;	
		
	}
	
	public String build(){
		builder.append("<!doctype html>");
		builder.append("<html>");
		builder.append("<head>");
		builder.append("</head>");
		builder.append("<body bgcolor=\"");
		builder.append(backgroundColor);
		builder.append("\">");		
		for(int i = 0; i < colors.length; i ++){		
			builder.append("<a href=\"");
			builder.append("/color");
			builder.append("/");
			builder.append(colors[i]);
			builder.append("\"");
			builder.append(">");
			builder.append(colors[i]);
			builder.append("</a>");
			builder.append("<br>");
		}
		
		
		builder.append("</body>");
		builder.append("</html>");
		
		return builder.toString();
	}

}
