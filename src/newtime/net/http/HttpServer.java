package newtime.net.http;

import java.io.IOException;
import java.net.Socket;

import newtime.net.http.control.ControllerManager;
import newtime.net.http.resource.ResourceManager;
import newtime.net.http.routing.Router;
import newtime.net.tcp.TcpServer;
import newtime.util.FileDictionary;

public class HttpServer extends TcpServer {

	public Router routes = new Router();
	public ResourceManager resources = new ResourceManager();
	public ControllerManager controllers = new ControllerManager();
	
	public static HttpServer host(int port) {
		FileDictionary.load("cfg/FileDictionary.cfg");
		HttpServer server = null;
		try {
			server = new HttpServer(port);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return server;
	}
	
	protected HttpServer(int port) throws IOException {
		super(port);
	}

	public HttpConnection onConnection(Socket socket) {
		HttpConnection connection = HttpConnection.create(this, socket);
		return connection;
	}
	
	
}
