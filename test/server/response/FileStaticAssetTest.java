package server.response;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	public void testFindPathURI(){
		URI uri = asset.findPathURI("/test/index");
		assertEquals(uri.toString(), "file:///test/index");
	}
	

	@Test
	public void testFindRelativePathRoot() {
		String absolutePathRoot = asset.findPathAbsolute("");
		assertNotNull(absolutePathRoot);
	}
	
	@Test
	public void testGetContent() throws MalformedURLException, UnsupportedEncodingException {	
		String content = "<!doctype html><html><head><title>Test "
				+ "at root</title></head><body>Test at root</body></html>";
		String contentReceived = asset.generate("/test.html");
		assertEquals(content, contentReceived);

	}

	
	@Test
	public void testGetFileContentOnRoutedPath() throws MalformedURLException, UnsupportedEncodingException{	
		String uriPath = "/test/index";
		String content = "<!doctype html><html><head><title>HTTP-Server-Service Test HTML</title></head><body>Test</body></html>";
		String contentReceived = asset.generate(uriPath);
		
		assertEquals(content, contentReceived);
	}
	
	
	
	@Test
	public void testGetContentOnPathWithFilename() throws MalformedURLException, UnsupportedEncodingException {
		String uriFilename = "/test/test.html";
		
		String content = "<!doctype html><html><head><title>HTTP-Server-Service Test HTML</title>"
				+ "</head><body><a href=\"http://www.scutigera.com\">Two</a> Mushroom in the Rain<br>with Ants Underneath.</body></html>";
		String contentReceived = asset.generate(uriFilename);
		assertEquals(content, contentReceived);

	}
}
