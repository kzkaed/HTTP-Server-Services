package server.response.assets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import server.Utilities;
import server.request.Request;
import server.response.Response;

public class ImageAsset implements Asset {
	
	private static final String CRLF = server.Constants.CRLF;

	public ImageAsset(){}

	@Override
	public boolean canHandle(Request request) {
		System.out.println("is image");
		return isImage(request.getURI());
	}

	@Override
	public Response execute(Request request) throws MalformedURLException,
			UnsupportedEncodingException {
		BufferedImage image = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] imageInBytes = null;
	
		File file = new File(Utilities.webrootAbsolutePath() + request.getURI());
		try {
			image = ImageIO.read(file);
			ImageIO.write(image, "jpg", baos);
			baos.flush();
			imageInBytes = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Path path = Utilities.webrootAbsolutePath() + request.getURI();
		//byte[] java.nio.file.Files.readAllBytes(Path path)
		
		
		return new Response("image",imageInBytes,null,buildResponseHeaders());
	}
	
	public String buildResponseHeaders() {
		String headers = "Server: Kristin Server" + CRLF
						+ "Accept-Ranges: bytes" + CRLF 
						+ "Content-Type: image/jpeg" + CRLF;
						//+ "Connection: Close" + CRLF;
	
		return headers;
	}
	
	public boolean isImage(String uri){
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String type = fileNameMap.getContentTypeFor(uri);
		if (type ==  null){
			return false;
		}
		return (type.contains("image"));	
	}

}
