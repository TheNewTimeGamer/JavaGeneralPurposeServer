package newtime.net.http.controllers;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.MethodNotAllowedHttpResponse;

public class Controller {

	public HttpResponse get(HttpConnection connection, HttpRequest request) {
		return new MethodNotAllowedHttpResponse();
	}
	
	public HttpResponse head(HttpConnection connection, HttpRequest request) {
		return new MethodNotAllowedHttpResponse();
	}
	
	public HttpResponse post(HttpConnection connection, HttpRequest request) {
		return new MethodNotAllowedHttpResponse();
	}	
	
	public HttpResponse put(HttpConnection connection, HttpRequest request) {
		return new MethodNotAllowedHttpResponse();
	}
	
	public HttpResponse delete(HttpConnection connection, HttpRequest request) {
		return new MethodNotAllowedHttpResponse();
	}
	
	public HttpResponse connect(HttpConnection connection, HttpRequest request) {
		return new MethodNotAllowedHttpResponse();
	}
	
	public HttpResponse options(HttpConnection connection, HttpRequest request) {
		return new MethodNotAllowedHttpResponse();
	}
	
	public HttpResponse trace(HttpConnection connection, HttpRequest request) {
		return new MethodNotAllowedHttpResponse();
	}
	
	public HttpResponse patch(HttpConnection connection, HttpRequest request) {
		return new MethodNotAllowedHttpResponse();
	}
	
}
