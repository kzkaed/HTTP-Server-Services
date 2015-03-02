package server.response;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DynamicAssetTest {


	@Test
	public void testGeneratesHtml() throws MalformedURLException, UnsupportedEncodingException {
		String uri = "/test/index";
		DynamicAsset asset = new DynamicAsset(uri);
		String html = asset.generatePage();
		assertEquals("<!doctype html><html><head></head><body></body></html>",html);
	}
	
	@Test
	public void testGeneratesHtmlwithDecodedParams() throws MalformedURLException, UnsupportedEncodingException {
		String uri = "/test/index?name=kristin&id=1&this=that+A%26B%3DC";
		DynamicAsset asset = new DynamicAsset(uri);
		String html = asset.generatePage();
		assertEquals("<!doctype html><html><head></head><body>this:that A&B=Cname:kristinid:1</body></html>",html);
	}

}
