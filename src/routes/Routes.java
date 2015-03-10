package routes;

public class Routes {
	
	private String uri;

	public Routes (String uri) {
		this.uri = uri;
	}
	
	public String getRoute() {
		String route;
		if(uri.contentEquals("/test/static")){
			route = "/test/index.html";
		}else if (uri.contentEquals("/") ){
			route = "/directory.html";	
		}else{
			route = uri;
		}	
		return route;
	}
	

}
