package server.response.assets;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import server.request.Request;
import server.response.Response;
import views.HtmlViewFactory;

@DisplayName("Parameter")
public class ParameterTest {
	private Asset asset;
	private Request request;

	@BeforeEach
	public void setUp() throws Exception {

		asset = new Parameter(new HtmlViewFactory());
		Map<String,String> params = new HashMap<>();
		params.put("variable_1","test1");
		params.put("variable_2", "test2");
		request = new Request("GET","/parameters","HTTP1/1", null, "GET /parameters?variable_1=test1&variable_2=test2 HTTP1/1",null,params);
	}

	@Nested
	@DisplayName("when checking if it can handle a request")
	class WhenCheckingIfItCanHandleARequest {

		@Test
		@DisplayName("returns true for a parameters request")
		void returns_true_for_a_parameters_request() {
			assertTrue(asset.canHandle(request), "should handle /parameters route");
		}
	}

	@Nested
	@DisplayName("when rendering parameters")
	class WhenRenderingParameters {

		@Test
		@DisplayName("includes all parameter key-value pairs in the body")
		void includes_all_parameter_key_value_pairs_in_the_body() throws MalformedURLException, UnsupportedEncodingException {
			Response response = asset.execute(request);
			String contentReceived = response.getBody();

			assertTrue(contentReceived.contains("variable_1 = test1"), "body should contain variable_1 = test1");
			assertTrue(contentReceived.contains("variable_2 = test2"), "body should contain variable_2 = test2");
		}
	}
}
