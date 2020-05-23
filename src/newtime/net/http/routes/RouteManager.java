package newtime.net.http.routes;

import java.util.ArrayList;

import newtime.net.http.HttpConnection;
import newtime.net.http.controllers.Controller;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;

public class RouteManager {

	public ArrayList<Route> routes = new ArrayList<Route>();
	
	public HttpResponse route(HttpConnection connection, HttpRequest request) {
		Route route = getRoute(request.action);
		if(route == null) {
			Controller defaultController = connection.getServerInstance().controllers.defaultController;
			return new Route(request.action, defaultController).invoke(connection, request);
		}
		return route.invoke(connection, request);
	}
	
	protected Route getRoute(String action) {
		for(Route route : routes) {
			if(route.action.equalsIgnoreCase(action)) {
				return route;
			}
		}
		return null;
	}
	
}
