package com.scutigera.color;

import java.io.Serializable;

public class ApplicationVariables implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int port;
	private static String webroot;
	private static String realpath;
	private static boolean status;
	
	
	public ApplicationVariables(){
		
		
	}
	
	static public boolean isSet(){
		return status;
	}

}
