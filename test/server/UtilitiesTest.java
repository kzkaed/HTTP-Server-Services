package server;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.Before;
import org.junit.Test;

import server.helpers.Utility;
import server.request.Request;
import server.response.assets.DirectoryAsset;

public class UtilitiesTest {


	@Before
	public void setUp() throws Exception {
		Context.PUBLIC_DIR_IN_USE = "public";
	}
	
	@Test
	public void testGETParse() {
		String request = "GET /public/index.html HTTP1/1";
		String delimiters = "[ ]+";
		String[] tokens = Utility.retreiveTokens(request,delimiters);
		assertEquals(tokens[1], "/public/index.html");
	}
	
	@Test
	public void testParseQueryWithManyParameters(){		
		ArrayList<String> list = new ArrayList<String>();
		String[] tokens = Utility.retreiveTokens("name=kristin&id=1", "&");
		for(int i = 0; i < tokens.length; i++){
			String[] tokens2 = Utility.retreiveTokens(tokens[i], "=");
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
		assertFalse(Utility.fileExist("/jam"));
	}
	
	@Test
	public void testDoesFileExistTrue(){
		assertTrue(Utility.fileExist("/file1"));
	}
	
	@Test
	public void testDoesRootFileExistFalse(){
		assertFalse(Utility.fileExist("/"));
	}
	
	
	@Test
	public void testfindPathURI() throws URISyntaxException{
		Path path = Paths.get("");
		URI uri = path.toUri();
		assertEquals(Utility.findServerPathURI(),uri);
	}
	
	@Test
	public void testFindPathAbsolute(){
		Path path = Paths.get("");
		assertEquals(Utility.findServerAbsolutePath(),path.toAbsolutePath().toString());
	}
	
	@Test 
	public void testGetAbsolutePath(){
		assertEquals(Utility.getAbsolutePath("/test/log.txt"),"/Users/kristin-8thlight/repos2/HTTP-Server-Services/test/log.txt");	
	}
	@Test
	public void testWebrootAbsolutePath(){
		Path path = Paths.get("");
		String thisserversPath = "/Users/kristin-8thlight/repos2/HTTP-Server-Services/public";
		String webrootConstruction = path.toAbsolutePath() +"/"+ server.Context.PUBLIC_DIR_IN_USE;
		assertEquals(Utility.webrootAbsolutePath(),webrootConstruction);
	}
}
