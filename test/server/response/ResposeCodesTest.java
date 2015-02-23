package server.response;

import static org.junit.Assert.*;

import org.junit.Test;

import server.response.ResponseCodes;



public class ResposeCodesTest {



	@Test
	public void test200() {
		String reasonPhrase = ResponseCodes.getReason("200");
		
		assertEquals(reasonPhrase,"OK");
	}
	
	@Test
	public void test500() {
		String reasonPhrase = ResponseCodes.getReason("500");
		
		assertEquals(reasonPhrase,"Internal Server Error");
	}

	
}
