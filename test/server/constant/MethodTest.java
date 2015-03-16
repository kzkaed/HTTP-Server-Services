package server.constant;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.constants.Method;

public class MethodTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGET() {
		assertEquals(Method.GET,"GET");
	}
	
	@Test
	public void testPOST() {
		assertEquals(Method.POST,"POST");
	}
	
	@Test
	public void testPUT() {
		assertEquals(Method.PUT,"PUT");
	}
	
	@Test
	public void testOPTIONS() {
		assertEquals(Method.OPTIONS,"OPTIONS");
	}
	@Test
	public void testHEAD() {
		assertEquals(Method.HEAD,"HEAD");
	}


}
