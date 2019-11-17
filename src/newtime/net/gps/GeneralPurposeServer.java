package newtime.net.gps;

import java.util.HashMap;

import newtime.net.auth.SessionManager;
import newtime.net.http.HttpConnection;
import newtime.net.http.HttpServer;
import newtime.net.http.control.Controller;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.routing.Route;
import newtime.net.http.view.View;
import newtime.net.tcp.kernel.Kernel;

public class GeneralPurposeServer {
	
	public static void main(String[] args) {
		new GeneralPurposeServer(80);
	}
	
	private HttpServer server;
		
	public GeneralPurposeServer(int port) {
		server = HttpServer.host(port);
		if(server == null) {
			System.err.println("Could not host server on port: " + port);
			return;
		}		
		Kernel kernel = new Kernel(server);

		
	}
	
	
}
