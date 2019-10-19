package newtime.net.websocket;

import java.io.IOException;
import java.net.Socket;

import newtime.net.tcp.TcpConnection;
import newtime.net.tcp.TcpServer;

public class WebSocketServer extends TcpServer{

	public static WebSocketServer host(int port) {
		WebSocketServer server = null;
		try {
			server = new WebSocketServer(port);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return server;
	}
	
	protected WebSocketServer(int port) throws IOException {
		super(port);
	}

	public TcpConnection onConnection(Socket socket) {
		WebSocketConnection connection = WebSocketConnection.create(this, socket);
		return connection;
	}
	
}
