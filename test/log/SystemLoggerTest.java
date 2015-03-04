package log;

import static org.junit.Assert.assertEquals;
import log.mocks.StringLogger;

import org.junit.Test;

public class SystemLoggerTest {

	
	@Test
	public void testOut() {
	    StringLogger log = new StringLogger();
	    log.log("Message");
	    assertEquals(log.logs.get(0), "Message");	    
	}
	
	@Test
	public void testError() {
	    StringLogger log = new StringLogger();
	    log.error("Message");
	    assertEquals(log.errors.get(0), "Message");	    
	}
	
}
