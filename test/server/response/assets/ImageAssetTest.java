package server.response.assets;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import server.Utilities;
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
		
		server.Constants.PUBLIC_DIR_IN_USE = "public";
		Request request = new Request();
		request.setURI("/image.jpeg");
		
		Utilities.webrootAbsolutePath();
		Path path = Paths.get(Utilities.webrootAbsolutePath()  + request.getURI());
		
		byte[] imageInBytes = null;
		String imageString = null;
		imageInBytes = Files.readAllBytes(path);
		imageString = Base64.encode(imageInBytes);
		assertNotNull(imageInBytes);
		assertNotNull(imageString);
		
		Response response = imgAsset.execute(request);
		assertNotNull(response.getBodyBytes());
		assertNotNull(response.getBody());
		
	}
	
	
}
