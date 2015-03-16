package com.scutigera.color;
import routes.AssetManager;
import server.Context;


public class Application {

	public static void setContext(int port, String publicDirectory){
		Context.setPort(port);
		Context.setPublicDirectory(publicDirectory);
	}
	
	public static AssetManager registerApplicationAssets(AssetManager manager){
		manager.register(new Color());	
		return manager;
	}
	
	
	
	
}
