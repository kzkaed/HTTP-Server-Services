package routes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RoutesTest {

	
	@Test
	public void test() {
		Routes route = new Routes();
		String documentPath = route.getRoute("/test/index");
		assertEquals(documentPath,"/test/index.html");
		
	}

}
