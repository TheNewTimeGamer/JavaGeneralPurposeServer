package newtime.net.http;

import java.io.IOException;
import java.net.Socket;

import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.BadRequestHttpResponse;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.InternalServerErrorHttpResponse;
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
		HttpResponse response = null;		
		
		if(request == null) {
			response = new BadRequestHttpResponse();
		}else {		
			response = ((HttpServer)this.server).route(this, request);
			if(response == null) {
				response = new InternalServerErrorHttpResponse();
			}
		}
				
		try {
			out.write(response.toBytes());
			out.flush();
		} catch (Exception e) {
			this.listening = false;
		}
	}
	
	public HttpServer getServerInstance() {
		return (HttpServer)this.server;
	}
	
}
