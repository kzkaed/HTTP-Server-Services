package com.scutigera.color;

public class ColorHtml {
	private StringBuilder builder;
	private String backgroundColor;
	
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
		builder.append("</body>");
		builder.append("</html>");
		
		return builder.toString();
	}

}
