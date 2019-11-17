package newtime.net.http.view;

public abstract class ViewManager {

	public abstract View get(String key);
	public abstract View put(String key, View view);
	public abstract View create(String key, String path);	
	
}
