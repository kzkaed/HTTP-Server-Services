package routes;

public class Routes {
	
	private static final String TEST_ROUTE = "/test/index";
	private static final String DEFAULT_INDEX = "/index.html";
	private static final String TEST_FILE = "/test.html";

	public Routes (){}
	
	public String getRoute(String uri){
	String route;
		
		if(uri.contentEquals(TEST_ROUTE)){
			route = "/test/index.html";
		}else if (uri.contentEquals(TEST_FILE)){
			route = TEST_FILE;		
		}else if (uri.contentEquals("/") ){
			route = DEFAULT_INDEX;
		}else{
			route = uri;
		}
		
		return route;
	}
	
	
	
	
	

}
