package newtime.net.http;

import java.io.IOException;
import java.net.Socket;

import newtime.net.http.control.ControllerManager;
import newtime.net.http.resource.ResourceManager;
import newtime.net.http.routing.RouteManager;
import newtime.net.tcp.TcpServer;
import newtime.util.FileDictionary;

public class HttpServer extends TcpServer {

	public RouteManager routes = new RouteManager();
	public ResourceManager resources = new ResourceManager();
	public ControllerManager controllers = new ControllerManager();
		
	public HttpServer(int port) throws IOException {
		super(port);
		resources.loadDefaults();
	}

	public HttpConnection onConnection(Socket socket) {
		HttpConnection connection = null;
		try {
			connection = new HttpConnection(this, socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
}
