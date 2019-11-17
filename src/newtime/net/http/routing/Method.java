package newtime.net.http.routing;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;

public interface Method {
	
	public HttpResponse perform(HttpConnection connection, HttpRequest request);
	
}