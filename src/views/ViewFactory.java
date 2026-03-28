package views;

import java.util.Hashtable;
import java.util.List;

public interface ViewFactory {

	View forDirectory(List<String> files);
	View forParameters(Hashtable<String,String> params);
	View forContent(String content);

}
