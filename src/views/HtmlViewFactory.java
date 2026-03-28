package views;

import java.util.Hashtable;
import java.util.List;

public class HtmlViewFactory implements ViewFactory {

	@Override
	public View forDirectory(List<String> files) {
		return new HtmlView(files);
	}

	@Override
	public View forParameters(Hashtable<String,String> params) {
		return new HtmlView(params);
	}

	@Override
	public View forContent(String content) {
		return new HtmlView(content);
	}

}
