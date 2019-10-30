package newtime.net.gps;

import java.util.HashMap;

import newtime.net.auth.SessionManager;
import newtime.net.http.HttpServer;
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
		
		String token = SessionManager.createSession("test");
		HashMap<String, Object> variables = SessionManager.getSessionVariables(token);
		variables.put("test", "This is a test");
		
	}
	
}
