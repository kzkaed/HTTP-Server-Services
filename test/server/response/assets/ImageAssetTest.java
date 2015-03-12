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

import server.Utilities;
import server.request.Request;

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
	public void imageOut() throws IOException{
		
		server.Constants.PUBLIC_DIR_IN_USE = "public";
		Request request = new Request();
		request.setURI("/image.jpeg");
		BufferedImage image = null;
		File file = new File(Utilities.webrootAbsolutePath() + request.getURI());
		
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(image.getClass());
		assertEquals(image.getType(),5);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos);
		baos.flush();
		byte[] imageInBytes = baos.toByteArray();
		baos.close();
		
		assertNotNull(imageInBytes);
		String imageAsString = new String(imageInBytes, "UTF8");
		
		
	}
}
