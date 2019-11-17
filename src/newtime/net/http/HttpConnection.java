package newtime.net.http;

import java.io.IOException;
import java.net.Socket;

import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.view.View;
import newtime.net.tcp.TcpConnection;

public class HttpConnection extends TcpConnection {

	public static HttpConnection create(HttpServer server, String ip, int port) {
		HttpConnection connection = null;
		try {
			connection = new HttpConnection(server, ip, port);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static HttpConnection create(HttpServer server, Socket socket) {
		HttpConnection connection = null;
		try {
			connection = new HttpConnection(server, socket);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	protected HttpConnection(HttpServer server, Socket socket) throws IOException {
		super(server, socket);
	}

	protected HttpConnection(HttpServer server, String ip, int port) throws IOException {
		super(server, ip, port);
	}
	
	public void onData(byte[] buffer) {
		HttpRequest request = HttpRequest.build(buffer);
		HttpResponse response = ((HttpServer)this.server).routes.route(this, request);				
		try {
			out.write(response.toBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HttpServer getServerInstance() {
		return (HttpServer)this.server;
	}
	
}
