package server.response.assets;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.Request;
import server.response.Response;
import server.response.assets.DynamicAsset;

public class DynamicAssetTest {


	@Test
	public void testGeneratesHtml() throws MalformedURLException, UnsupportedEncodingException {
		String uri = "/test/dynamic";
		DynamicAsset asset = new DynamicAsset();
		Request request = new Request(uri);
		Response response = asset.execute(request);
		String html = response.getBody();
		
		String expect = "<!doctype html><html><head></head><body>test dynamic</body></html>";
		
		assertEquals(expect,html);
	}
	
	
	


}
