package com.scutigera.color;

import java.io.Serializable;

public class ApplicationVariables implements Serializable {

	private static final long serialVersionUID = 1L;
	private static boolean status;
	
	static public boolean isSet(){
		return status;
	}

}
