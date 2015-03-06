package server.response;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.Request;

public class DynamicAssetTest {


	@Test
	public void testGeneratesHtml() throws MalformedURLException, UnsupportedEncodingException {
		String uri = "/test/dynamic";
		DynamicShowParamsAsset asset = new DynamicShowParamsAsset();
		Request request = new Request(uri);
		String html = asset.render(request);
		
		String expect = "<!doctype html><html><head></head><body>test dynamic</body></html>";
		
		assertEquals(expect,html);
	}
	
	
	


}
