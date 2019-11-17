package newtime.net.http.view;

public interface ViewManager {

	public View get(String key);
	public View put(String key, View view);
	public View create(String key, String path);	
	
}
