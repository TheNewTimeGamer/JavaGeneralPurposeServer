package newtime.net.http.routes;

import newtime.net.http.HttpConnection;
import newtime.net.http.controllers.Controller;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.MethodNotImplementedHttpResponse;

public class Route {

	public final String action;
	public final Controller controller;
	
	public Route(String action, Controller controller) {
		this.action = action;
		this.controller = controller;
	}
	
	public HttpResponse invoke(HttpConnection connection, HttpRequest request) {
		switch(request.method) {
			case "GET":
				return controller.get(connection, request);
			case "HEAD":
				return controller.head(connection, request);
			case "POST":
				return controller.post(connection, request);
			case "PUT":
				return controller.put(connection, request);
			case "DELETE":
				return controller.delete(connection, request);
			case "CONNECT":
				return controller.connect(connection, request);
			case "OPTIONS":
				return controller.options(connection, request);
			case "TRACE":
				return controller.trace(connection, request);
			case "PATCH":
				return controller.patch(connection, request);
		}
		return new MethodNotImplementedHttpResponse();
	}
	
	
	
}
