package routes;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.Test;

public class RoutesTest {

	
	@Test
	public void test() throws MalformedURLException, UnsupportedEncodingException {
		Routes route = new Routes("/test/index");
		String documentPath = route.getRoute();
		assertEquals(documentPath,"/test/index.html");
		
	}

}
