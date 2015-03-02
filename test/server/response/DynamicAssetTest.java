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
		String uri = "/test/index";
		DynamicAsset asset = new DynamicAsset();
		Request request = new Request(uri);
		String html = asset.render(request);
		assertEquals("<!doctype html><html><head></head><body>Test</body></html>",html);
	}
	


}
