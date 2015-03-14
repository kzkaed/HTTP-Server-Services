package server.response.assets;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.Request;

public class GetTest {

	Asset get;
	@Before
	public void setUp() throws Exception {
		get = new Get();
	}

	@Test
	public void testReturnsTrueIfGetMethod() {
		
		Request request = new Request();
		request.setMethod("GET");
		assertTrue(get.canHandle(request));
	}
	
	@Test
	public void testExecuteUnimplemented(){
		
	}

}
