package newtime.net.http.routing;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;

public interface IRouter {

	public abstract HttpResponse route(HttpConnection connection, HttpRequest request);
	
}
