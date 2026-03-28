package views;

public class ContentView implements View {

	private final String content;

	public ContentView(String content) {
		this.content = content;
	}

	@Override
	public String build() {
		return "<!doctype html><html><head></head><body>" + content + "</body></html>";
	}

}
