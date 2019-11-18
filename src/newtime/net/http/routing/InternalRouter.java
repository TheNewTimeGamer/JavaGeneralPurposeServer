package newtime.net.http.routing;

import java.util.HashMap;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.view.ViewManager;

public class InternalRouter extends Router {

	public HashMap<String, Route> routes = new HashMap<String, Route>();
	
	public HttpResponse route(HttpConnection connection, HttpRequest request) {
		Route route = routes.get(request.action);
		if(route == null) {
			return ViewManager.FILE_NOT_FOUND.build();
		}
		switch(request.method) {
			case "GET":
				if(route.get == null) {break;}
				return route.get.perform(connection, request);
			case "POST":
				if(route.post == null) {break;}
				return route.post.perform(connection, request);
			case "HEAD":
				if(route.head == null) {break;}
				return route.head.perform(connection, request);
			case "PUT":
				if(route.put == null) {break;}
				return route.put.perform(connection, request);
			case "DELETE":
				if(route.delete == null) {break;}
				return route.delete.perform(connection, request);
			case "CONNECT":
				if(route.connect == null) {break;}
				return route.connect.perform(connection, request);
			case "OPTIONS":
				if(route.options == null) {break;}
				return route.options.perform(connection, request);
			case "TRACE":
				if(route.trace == null) {break;}
				return route.trace.perform(connection, request);
			case "PATCH":
				if(route.patch == null) {break;}
				return route.patch.perform(connection, request);
		}
		return ViewManager.METHOD_NOT_IMPLEMENTED.build();
	}
	
	public Route createRoute(String action) {
		Route route = routes.get(action);
		if(route == null) {
			route = new Route();
			routes.put(action, route);
		}
		return route;
	}
	
	public Route createRoute(String action, Method method) {
		Route route = createRoute(action);
		route.get = method;
		route.post = method;
		route.head = method;
		route.put = method;
		route.delete = method;
		route.connect = method;
		route.options = method;
		route.trace = method;
		route.patch = method;
		return route;
	}
		
}
