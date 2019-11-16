package newtime.net.http.routing;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;

public class Route {

	public Method get;
	public Method post;
	public Method head;
	public Method put;
	public Method delete;
	public Method connect;
	public Method options;
	public Method trace;
	public Method patch;
	
}

interface Method {
	
	public HttpResponse perform(HttpConnection connection, HttpRequest request);
	
}