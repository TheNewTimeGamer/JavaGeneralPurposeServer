package newtime.net.http.routing;

import java.util.ArrayList;

import newtime.net.http.HttpConnection;
import newtime.net.http.control.Controller;
import newtime.net.http.control.GenericController;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.HttpResponseMethodNotImplemented;
import newtime.net.http.response.HttpResponseNotFound;

public class Router {

	public Controller defaultController = new GenericController();
	public ArrayList<Route> routes = new ArrayList<Route>();
	
	public Router() {
		Route test = new Route("/test", new GenericController());
		routes.add(test);
	}
	
	public HttpResponse route(HttpConnection connection, HttpRequest request) {
		Controller controller = getController(request);
		if(controller == null) {
			return new HttpResponseNotFound();
		}		
		switch(request.method) {
			case "GET":
				return controller.get(connection, request);
			case "POST":
				return controller.post(connection, request);
			case "HEAD":
				return controller.head(connection, request);
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
			default:
				return new HttpResponseMethodNotImplemented();
		}
	}
	
	protected Controller getController(HttpRequest request) {
		for(int i = 0; i < routes.size(); i++) {
			if(routes.get(i).action.equals(request.action)) {
				return routes.get(i).controller;
			}
		}
		if(defaultController != null) {
			return defaultController;
		}
		return null;
	}
	
}
