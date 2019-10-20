package newtime.net.http.control;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.HttpResponseMethodNotAllowed;

public class GenericController implements Controller {
	
	public HttpResponse get(HttpConnection connection, HttpRequest request) {
		HttpResponse response = new HttpResponse();
				
		
		return response;
	}	
	public HttpResponse post(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse head(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse put(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse delete(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse connect(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse options(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse trace(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse patch(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	
}