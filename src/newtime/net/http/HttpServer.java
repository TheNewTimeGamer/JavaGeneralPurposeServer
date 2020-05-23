package newtime.net.http;

import java.io.IOException;
import java.net.Socket;

import newtime.net.http.controllers.ControllerManager;
import newtime.net.http.routes.RouteManager;
import newtime.net.tcp.TcpServer;
import newtime.util.FileDictionary;

public class HttpServer extends TcpServer {

	public RouteManager routeManage = new RouteManager();
	public ControllerManager controllerManager = new ControllerManager();
		
	public HttpServer(int port) throws IOException {
		super(port);
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
