package server;

import static org.junit.Assert.*;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
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

}
