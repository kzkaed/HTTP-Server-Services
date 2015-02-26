package server.response;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
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
	private String testRelativePath;
	@Before
	public void setUp()  {
		asset = new FileStaticAsset();
		Path currentRelativePath = Paths.get("");
		testRelativePath = currentRelativePath.toAbsolutePath().toString();
		
	}
	@Test
	public void testFindPathURI(){
		URI uri = asset.findPathURI("/test/index");
		assertEquals(uri.toString(), "file:///test/index");
	}
	

	@Test
	public void testFindRelativePathRoot() {
		String relativePathRoot = asset.findPathAbsolute("");
		assertNotNull(relativePathRoot);
	}
	
	@Test
	public void testGetContent() throws MalformedURLException {	
		String content = "<!doctype html><html><head><title>Test "
				+ "at root</title></head><body>Test at root</body></html>";
		String contentReceived = asset.getResponseBody("/test.html");
		assertEquals(content, contentReceived);

	}

	
	@Test
	public void testGetFileContentOnRoutedPath() throws MalformedURLException{	
		String uriPath = "/test/index";
		String content = "<!doctype html><html><head><title>HTTP-Server-Service Test HTML</title></head><body>Test</body></html>";
		String contentReceived = asset.getResponseBody(uriPath);
		
		assertEquals(content, contentReceived);
	}
	
	
	
	@Test
	public void testGetContentOnPathWithFilename() throws MalformedURLException {
		String uriFilename = "/test/test.html";
		
		String content = "<!doctype html><html><head><title>HTTP-Server-Service Test HTML</title>"
				+ "</head><body><a href=\"http://www.scutigera.com\">Two</a> Mushroom in the Rain<br>with Ants Underneath.</body></html>";
		String contentReceived = asset.getResponseBody(uriFilename);
		assertEquals(content, contentReceived);

	}
}
