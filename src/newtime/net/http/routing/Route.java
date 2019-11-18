package newtime.net.http.routing;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.view.ViewManager;

public class Route {

	public static Method METHOD_NOT_ALLOWED = (HttpConnection connection, HttpRequest request) -> {
		return ViewManager.METHOD_NOT_ALLOWED.build();
	};
	
	public Method get = METHOD_NOT_ALLOWED;
	public Method post = METHOD_NOT_ALLOWED;
	public Method head = METHOD_NOT_ALLOWED;
	public Method put = METHOD_NOT_ALLOWED;
	public Method delete = METHOD_NOT_ALLOWED;
	public Method connect = METHOD_NOT_ALLOWED;
	public Method options = METHOD_NOT_ALLOWED;
	public Method trace = METHOD_NOT_ALLOWED;
	public Method patch = METHOD_NOT_ALLOWED;
	
}
