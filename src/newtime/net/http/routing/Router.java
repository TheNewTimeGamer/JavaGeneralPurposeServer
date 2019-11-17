package newtime.net.http.routing;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;

public abstract class Router {

	public abstract HttpResponse route(HttpConnection connection, HttpRequest request);
	public abstract Route createRoute(String action);
	public abstract Route createRoute(String action, Method method);
	
}
