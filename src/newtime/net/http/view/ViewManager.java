package newtime.net.http.view;

import java.util.HashMap;

public class ViewManager {

	private HashMap<String, View> views = new HashMap<String, View>();

	public ViewManager() {
		views.put("FILE_NOT_FOUND", new View("http/default/FileNotFound.html"));
		views.put("METHOD_NOT_ALLOWED", new View("http/default/MethodNotAllowed.html"));
		views.put("METHOD_NOT_IMPLEMENTED", new View("http/default/MethodNotImplemented.html"));
	}
	
	public View get(String key) {
		return views.get(key);
	}
	
	public View put(String key, View view) {
		return views.put(key, view);
	}
	
	public View create(String name, String path) {
		View view = new View(path);
		views.put(name, view);
		return view;
	}
	
}
