package views;

import java.util.Hashtable;
import java.util.Set;

public class ParameterView implements View {

	private final Hashtable<String, String> params;

	public ParameterView(Hashtable<String, String> params) {
		this.params = params;
	}

	@Override
	public String build() {
		StringBuilder builder = new StringBuilder();
		builder.append("<!doctype html><html><head></head><body>");
		if (params.containsKey("variable_1")) {
			builder.append("variable_1 = " + params.get("variable_1"));
			builder.append("variable_2 = " + params.get("variable_2"));
		} else {
			Set<String> keys = params.keySet();
			for (String key : keys) {
				builder.append(key + ":" + params.get(key) + "<br>");
			}
		}
		builder.append("</body></html>");
		return builder.toString();
	}

}
