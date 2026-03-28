package views;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ParameterViewTest {

	@Test
	public void testRendersNamedVariables() {
		Map<String, String> params = new HashMap<>();
		params.put("variable_1", "value1");
		params.put("variable_2", "value2");
		String result = new ParameterView(params).build();
		assertEquals("<!doctype html><html><head></head><body>variable_1 = value1variable_2 = value2</body></html>", result);
	}

	@Test
	public void testRendersArbitraryParams() {
		Map<String, String> params = new HashMap<>();
		params.put("color", "blue");
		String result = new ParameterView(params).build();
		assertTrue(result.contains("color:blue<br>"));
	}

}
