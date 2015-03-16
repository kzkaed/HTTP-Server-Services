package server.constants;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
import server.constants.Method;


public class MethodTest {
	
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
