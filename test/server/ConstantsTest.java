package server;

import static org.junit.Assert.*;


import java.net.InetAddress;

import java.net.UnknownHostException;

import org.junit.Test;

public class ConstantsTest {

	@Test
	public void testSetPort() {
		Constants.setPort(5000);
		assertEquals(5000, Constants.getPort());
	}
	
	@Test
	public void testSetHost() {
		String host = "not able to be determined";
		try {
			host = InetAddress.getLocalHost().getHostName().toString();	
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		assertEquals(host, Constants.HOST);
	}
	
	@Test 
	public void testSetPublicDirectory(){
		Constants.setPublicDirectory("greekTragedies");
		assertEquals("greekTragedies", Constants.getPublicDirectory());
	}


}
