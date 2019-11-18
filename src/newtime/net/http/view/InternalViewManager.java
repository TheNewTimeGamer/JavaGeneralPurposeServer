package newtime.net.http.view;

import java.util.HashMap;

public class InternalViewManager extends ViewManager {

	private HashMap<String, View> views = new HashMap<String, View>();
	
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
