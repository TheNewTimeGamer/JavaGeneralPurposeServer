package newtime.debug;

import newtime.net.http.HttpServer;
import newtime.net.websocket.WebSocketServer;

public class GeneralDebug {

	public static void main(String[] args) {
		new GeneralDebug();
	}
	
	public GeneralDebug() {
		//HttpServer server = HttpServer.host(80);
		WebSocketServer server = WebSocketServer.host(80);
				
	}
	
}
