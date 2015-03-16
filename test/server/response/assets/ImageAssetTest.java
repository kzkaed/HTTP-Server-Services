package server.response.assets;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.helpers.Utility;
import server.request.Request;
import server.response.Response;

public class ImageAssetTest {

	private ImageAsset imgAsset;
	@Before
	public void setUp() throws Exception {
		imgAsset = new ImageAsset();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCanHandleAnImageJpeg() {
		Request request = new Request();
		request.setURI("/image.jpeg");		
		boolean handle = imgAsset.canHandle(request);
		
		assertTrue(handle);	
	}
	
	@Test
	public void testCanHandleAnImageGif() {
		Request request = new Request();
		request.setURI("/image.gif");		
		boolean handle = imgAsset.canHandle(request);
		
		assertTrue(handle);	
	}
	
	@Test
	public void testCanHandleAnImagePng() {
		Request request = new Request();
		request.setURI("/image.png");		
		boolean handle = imgAsset.canHandle(request);
		
		assertTrue(handle);	
	}
	
	@Test
	public void testkGetMime() throws IOException{
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String type = fileNameMap.getContentTypeFor("/image.jpeg");
	
		assertEquals(type,"image/jpeg");
	}

	
	@Test
	public void imageToBytes() throws IOException{
		
		server.Context.PUBLIC_DIR_IN_USE = "public";
		Request request = new Request();
		request.setURI("/image.jpeg");
		
		Utility.webrootAbsolutePath();
		Path path = Paths.get(Utility.webrootAbsolutePath()  + request.getURI());
		
		byte[] imageInBytes = Files.readAllBytes(path);
				
		assertNotNull(imageInBytes);

		Response response = imgAsset.execute(request);
		assertArrayEquals(imageInBytes, response.getBodyBytes());
		assertEquals("image: " + request.getURI(), response.getBody());
		
	}
	
	
}
