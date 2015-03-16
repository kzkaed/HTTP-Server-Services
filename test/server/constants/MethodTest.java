package server.constants;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.constants.Method;
import server.request.Request;
import server.response.ResponseBuilder;

public class MethodTest {
	
	@Before
	public void setUp() throws Exception {
		
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

	@Test
	public void testIfMethodIsNOTImplemented() throws IOException{
		assertFalse(Method.isMethodImplemented("XYZ"));		
	}
	
	@Test
	public void testIfMethodIsImplemented() throws IOException{
		assertTrue(Method.isMethodImplemented("GET"));	
	}


}
