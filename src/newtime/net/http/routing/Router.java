package newtime.net.http.routing;

import java.util.HashMap;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;

public class Router {

	public HashMap<String, Route> routes = new HashMap<String, Route>();
	
	public HttpResponse route(HttpConnection connection, HttpRequest request) {
		Route route = routes.get(request.action);
		if(route == null) {
			return connection.getServerInstance().viewManager.views.get("error404").build();
		}
		switch(request.method) {
			case "GET":
				route.get.perform(connection, request);
				break;
			case "POST":
				route.post.perform(connection, request);
				break;
			case "HEAD":
				route.head.perform(connection, request);
				break;
			case "PUT":
				route.put.perform(connection, request);
				break;
			case "DELETE":
				route.delete.perform(connection, request);
				break;
			case "CONNECT":
				route.connect.perform(connection, request);
				break;
			case "OPTIONS":
				route.options.perform(connection, request);
				break;
			case "TRACE":
				route.trace.perform(connection, request);
				break;
			case "PATCH":
				route.patch.perform(connection, request);
				break;
		}
		return connection.getServerInstance().viewManager.views.get("error501").build();
	}
	
}
