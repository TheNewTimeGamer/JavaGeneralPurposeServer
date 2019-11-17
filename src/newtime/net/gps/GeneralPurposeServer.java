package newtime.net.gps;

import newtime.net.http.HttpServer;
import newtime.net.http.control.ExternalControllerManager;
import newtime.net.http.routing.ExternalRouter;
import newtime.net.http.view.ExternalViewManager;
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

		server.views = new ExternalViewManager();
		server.controllers = new ExternalControllerManager();
		server.routes = new ExternalRouter();
		
	}
	
}
