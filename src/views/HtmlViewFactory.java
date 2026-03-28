package views;

import java.util.List;
import java.util.Map;

public class HtmlViewFactory implements ViewFactory {

	@Override
	public View forDirectory(List<String> files) {
		return new DirectoryView(files);
	}

	@Override
	public View forParameters(Map<String,String> params) {
		return new ParameterView(params);
	}

	@Override
	public View forContent(String content) {
		return new ContentView(content);
	}

}
