package newtime.debug;

import newtime.net.http.HttpServer;

public class GeneralDebug {

	public static void main(String[] args) {
		new GeneralDebug();
	}
	
	public GeneralDebug() {
		HttpServer server = HttpServer.host(80);
				
	}
	
}
