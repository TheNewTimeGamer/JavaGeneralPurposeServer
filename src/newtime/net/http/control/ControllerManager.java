package newtime.net.http.control;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.view.View;

public interface ControllerManager {

	public View invoke(HttpConnection connection, HttpRequest request, String view);
	
}
