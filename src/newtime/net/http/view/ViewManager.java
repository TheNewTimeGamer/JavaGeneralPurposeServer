package newtime.net.http.view;

import java.io.File;

public abstract class ViewManager {

	public static final View FILE_NOT_FOUND = new View("404 NOT FOUND", "text/html", "<b>404 NOT FOUND</b>");
	public static final View METHOD_NOT_ALLOWED = new View("405 METHOD NOT ALLOWED", "text/html", "405 METHOD NOT ALLOWED");
	public static final View METHOD_NOT_IMPLEMENTED = new View("501 METHOD NOT IMPLEMENTED", "text/html", "501 METHOD NOT IMPLEMENTED");
	
	public abstract View get(String key);
	public abstract View put(String key, View view);
	public abstract View create(String key, String path);	
	
}
