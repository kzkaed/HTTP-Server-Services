package views;

import java.util.List;
import java.util.Map;

public interface ViewFactory {

	View forDirectory(List<String> files);
	View forParameters(Map<String,String> params);
	View forContent(String content);

}
