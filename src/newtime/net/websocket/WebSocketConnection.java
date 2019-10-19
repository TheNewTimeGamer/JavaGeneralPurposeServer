package newtime.net.websocket;

import java.io.IOException;
import java.net.Socket;

import newtime.net.tcp.TcpConnection;

public class WebSocketConnection extends TcpConnection {

	public static WebSocketConnection create(WebSocketServer server, Socket socket) {
		WebSocketConnection connection = null;
		try {
			connection = new WebSocketConnection(server, socket);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	protected WebSocketConnection(WebSocketServer server, Socket socket) throws IOException {
		super(server, socket);
	}

	protected void onData(byte[] data) {
		System.out.println(new String(data));
	}

	
}
