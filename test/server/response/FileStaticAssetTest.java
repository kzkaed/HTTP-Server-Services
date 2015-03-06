package server.response;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.Request;

public class FileStaticAssetTest {

	private FileStaticAsset asset;
	
	@Before
	public void setUp()  {
		asset = new FileStaticAsset();		
	}
	
		
	@Test
	public void testGetContent() throws MalformedURLException, UnsupportedEncodingException {	
		String content = "file1 contents";
		Request request = new Request("GET","/file1","HTTP1/1", null, "GET /file1 HTTP1/1",null,new Hashtable<String,String>());
		String contentReceived = asset.render(request);
		
		assertEquals(content, contentReceived);
	}

	
	@Test
	public void testGetFileContentOnRoutedPath() throws MalformedURLException, UnsupportedEncodingException{	
		String content = "<!doctype html><html><head></head><body>Test Static</body></html>";
		Request request = new Request("GET","/test/static","HTTP1/1", null, "GET /test/static HTTP1/1",null,new Hashtable<String,String>());
		String contentReceived = asset.render(request);
		
		assertEquals(content, contentReceived);
	}
	
	
	
}
