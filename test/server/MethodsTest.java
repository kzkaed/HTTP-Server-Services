package server;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.constants.Methods;

public class MethodsTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGET() {
		assertEquals(Methods.GET,"GET");
	}
	
	@Test
	public void testPOST() {
		assertEquals(Methods.POST,"POST");
	}
	
	@Test
	public void testPUT() {
		assertEquals(Methods.PUT,"PUT");
	}
	
	@Test
	public void testOPTIONS() {
		assertEquals(Methods.OPTIONS,"OPTIONS");
	}
	@Test
	public void testHEAD() {
		assertEquals(Methods.HEAD,"HEAD");
	}


}
