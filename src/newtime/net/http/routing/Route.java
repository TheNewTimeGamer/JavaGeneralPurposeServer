package newtime.net.http.routing;

import newtime.net.http.control.Controller;

public class Route {

	final String action;
	final Controller controller;
	
	public Route(String action, Controller controller) {
		this.action = action;
		this.controller = controller;
	}
		
}
