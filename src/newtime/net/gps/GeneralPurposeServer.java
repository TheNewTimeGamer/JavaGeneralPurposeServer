package newtime.net.gps;

import java.io.IOException;

import newtime.net.http.HttpServer;
import newtime.net.tcp.kernel.Kernel;

public class GeneralPurposeServer {
	
	public static void main(String[] args) {
		try {
			new GeneralPurposeServer(80);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private HttpServer server;
		
	public GeneralPurposeServer(int port) throws IOException {
		server = new HttpServer(port);
		if(server == null) {
			System.err.println("Could not host server on port: " + port);
			return;
		}
		Kernel kernel = new Kernel(server);
		
	}
	
}
