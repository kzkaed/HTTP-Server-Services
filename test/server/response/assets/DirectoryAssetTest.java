package server.response.assets;

import static org.junit.Assert.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.Constants;
import server.Utilities;
import server.request.Request;
import server.response.Response;

public class DirectoryAssetTest {

	private Asset directoryAsset;
	private Request request;
	
	@Before
	public void setUp() throws Exception {
		Constants.PUBLIC_DIR_IN_USE = "public";
		directoryAsset = new DirectoryAsset();
		request = new Request("GET","/","HTTP1/1", null, "GET / HTTP1/1",null,new Hashtable<String,String>());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCanHandle() {
		assertTrue(directoryAsset.canHandle(request));
	}
	
	@Test
	public void testFilesListed() throws MalformedURLException, UnsupportedEncodingException{
		String directory = server.Utilities.getAbsolutePath("/public");
		File[] files = new File(directory).listFiles();
		List<String> results = new ArrayList<String>();
		for (File file : files) {
		    if (file.isFile()) {
		        results.add(file.getName()); 
		    }
		}
		Response response = directoryAsset.render(request);
		String contentReceived = response.getBody();
		
		assertNotNull(results);
	}
	
	@Test
	public void testRendersView(){
		
		
	}

}
