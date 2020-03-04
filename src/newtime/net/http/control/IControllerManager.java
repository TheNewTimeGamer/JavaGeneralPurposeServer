package newtime.net.http.control;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;

public interface IControllerManager {

	public abstract HttpResponse invoke(HttpConnection connection, HttpRequest request, String view);
	
}
