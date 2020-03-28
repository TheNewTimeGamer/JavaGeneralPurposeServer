package newtime.net.http;

import java.io.IOException;
import java.net.Socket;

import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpQuickResponse;
import newtime.net.http.response.HttpResponse;
import newtime.net.tcp.TcpConnection;

public class HttpConnection extends TcpConnection {
	
	public HttpConnection(HttpServer server, Socket socket) throws IOException {
		super(server, socket);
	}

	public HttpConnection(String ip, int port) throws IOException {
		super(ip, port);
	}
	
	public void onData(byte[] buffer) {
		HttpRequest request = HttpRequest.build(buffer);
		HttpResponse response = ((HttpServer)this.server).routes.route(this, request);
		
		if(response == null) {return;}
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
