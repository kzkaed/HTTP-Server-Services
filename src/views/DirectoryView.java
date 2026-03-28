package views;

import java.util.List;

public class DirectoryView implements View {

	private final List<String> files;

	public DirectoryView(List<String> files) {
		this.files = files;
	}

	@Override
	public String build() {
		StringBuilder builder = new StringBuilder();
		builder.append("<!doctype html><html><head></head><body>");
		builder.append("Directory<br>");
		for (String file : files) {
			builder.append("<a href=\"" + file + "\">" + file + "</a><br>");
		}
		builder.append("</body></html>");
		return builder.toString();
	}

}
