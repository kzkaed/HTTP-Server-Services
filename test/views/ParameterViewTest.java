package views;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("ParameterView")
public class ParameterViewTest {

	@Nested
	@DisplayName("when rendering parameters")
	class WhenRenderingParameters {

		@Test
		@DisplayName("renders named variables as key-value pairs")
		void renders_named_variables_as_key_value_pairs() {
			Map<String, String> params = new HashMap<>();
			params.put("variable_1", "value1");
			params.put("variable_2", "value2");
			String result = new ParameterView(params).build();
			assertEquals("<!doctype html><html><head></head><body>variable_1 = value1variable_2 = value2</body></html>", result, "should render all variables in the body");
		}

		@Test
		@DisplayName("renders arbitrary parameters with colon format")
		void renders_arbitrary_parameters_with_colon_format() {
			Map<String, String> params = new HashMap<>();
			params.put("color", "blue");
			String result = new ParameterView(params).build();
			assertTrue(result.contains("color:blue<br>"), "should contain the parameter in key:value<br> format");
		}
	}
}
