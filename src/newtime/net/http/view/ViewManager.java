package newtime.net.http.view;

public abstract class ViewManager {

	public static final View FILE_NOT_FOUND = new View("http/defaults/FileNotFound.html");
	public static final View METHOD_NOT_ALLOWED = new View("http/defaults/MethodNotAllowed.html");
	public static final View METHOD_NOT_IMPLEMENTED = new View("http/defaults/MethodNotImplemented.html");
	
	public abstract View get(String key);
	public abstract View put(String key, View view);
	public abstract View create(String key, String path);	
	
}
