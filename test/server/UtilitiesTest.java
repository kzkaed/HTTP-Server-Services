package server;

import static org.junit.Assert.*;


import java.util.ArrayList;
import org.junit.Test;

public class UtilitiesTest {

	
	@Test
	public void testGETParse() {
		String request = "GET /public/index.html HTTP1/1";
		String delimiters = "[ ]+";
		String[] tokens = Utilities.retreiveTokens(request,delimiters);
		assertEquals(tokens[1], "/public/index.html");
	}
	
	@Test
	public void testParseQueryWithManyParameters(){		
		ArrayList<String> list = new ArrayList<String>();
		String[] tokens = Utilities.retreiveTokens("name=kristin&id=1", "&");
		for(int i = 0; i < tokens.length; i++){
			String[] tokens2 = Utilities.retreiveTokens(tokens[i], "=");
			for(int j = 0; j < tokens2.length; j++){
				list.add(tokens2[j]);;
			}
			
		}
		assertEquals(tokens[0],"name=kristin");
		assertEquals(tokens[1],"id=1");
		assertEquals(list.get(0),"name");
		assertEquals(list.get(1),"kristin");
		assertEquals(list.get(2),"id");
		assertEquals(list.get(3),"1");	
	}
	
	@Test
	public void testFileExistFalse(){
		assertFalse(Utilities.doesFileExist("/jam"));
	}
	
	@Test
	public void testDoesFileExistTrue(){
		assertTrue(Utilities.doesFileExist("/file1"));
	}
	
	
	
	
	
	
}
