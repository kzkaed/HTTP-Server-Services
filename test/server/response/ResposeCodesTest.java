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
	public void test201() {
		String reasonPhrase = ResponseCodes.getReason("201");
		assertEquals(reasonPhrase,"Created");
	}
	
	@Test
	public void test202() {
		String reasonPhrase = ResponseCodes.getReason("202");
		assertEquals(reasonPhrase,"Accepted");
	}
	
	@Test
	public void test404() {
		String reasonPhrase = ResponseCodes.getReason("404");
		assertEquals(reasonPhrase,"Not Found");
	}
	
	@Test
	public void test500() {
		String reasonPhrase = ResponseCodes.getReason("500");
		assertEquals(reasonPhrase,"Internal Server Error");
	}
	
	@Test
	public void test502() {
		String reasonPhrase = ResponseCodes.getReason("502");
		assertEquals(reasonPhrase,"Not Implemented");
	}
	


	
	
}
