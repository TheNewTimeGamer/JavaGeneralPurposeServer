package newtime.net.http.control;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.HttpResponseMethodNotAllowed;

public interface Controller {

	public abstract HttpResponse get(HttpConnection connection, HttpRequest request);
	public abstract HttpResponse post(HttpConnection connection, HttpRequest request);
	public abstract HttpResponse head(HttpConnection connection, HttpRequest request);
	public abstract HttpResponse put(HttpConnection connection, HttpRequest request);
	public abstract HttpResponse delete(HttpConnection connection, HttpRequest request); 
	public abstract HttpResponse connect(HttpConnection connection, HttpRequest request);
	public abstract HttpResponse options(HttpConnection connection, HttpRequest request);
	public abstract HttpResponse trace(HttpConnection connection, HttpRequest request);
	public abstract HttpResponse patch(HttpConnection connection, HttpRequest request);
	
}
