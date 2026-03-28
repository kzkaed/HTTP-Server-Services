package server.response.assets;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.request.Request;
import server.response.Response;
import views.HtmlViewFactory;

public class ParameterTest {
	private Asset asset;
	private Request request;
	
	@Before
	public void setUp() throws Exception {
		
		asset = new Parameter(new HtmlViewFactory());
		Map<String,String> params = new HashMap<>();
		params.put("variable_1","test1");
		params.put("variable_2", "test2");
		request = new Request("GET","/parameters","HTTP1/1", null, "GET /parameters?variable_1=test1&variable_2=test2 HTTP1/1",null,params);
	}

	@Test
	public void testCanHandle() {
		assertTrue(asset.canHandle(request));
	}
	
	@Test
	public void testRender() throws MalformedURLException, UnsupportedEncodingException {	
		Response response = asset.execute(request);		
		String contentReceived = response.getBody();
		
		assertTrue(contentReceived.contains("variable_1 = test1"));
		assertTrue(contentReceived.contains("variable_2 = test2"));
	}

}
