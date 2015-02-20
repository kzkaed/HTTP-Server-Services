package log.mocks;

import java.util.ArrayList;
import java.util.List;

import log.Logger;


public class StringLogger implements Logger{

	public List<String> logs;
	public List<String> errors;
	
	public StringLogger(){
		this.logs = new ArrayList<String>();
		this.errors = new ArrayList<String>();
	}

	@Override
	public void log(String message) {
		this.logs.add(message);	
	}

	@Override
	public void error(String message) {
		this.errors.add(message);	
	}

}
