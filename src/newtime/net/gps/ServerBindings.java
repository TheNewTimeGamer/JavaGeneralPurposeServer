package newtime.net.gps;

import java.io.IOException;

import org.graalvm.polyglot.Context;

import newtime.net.http.HttpServer;
import newtime.net.tcp.TcpServer;

public class ServerBindings {
	
	private Context mainContext;
	
	public ServerBindings(Context mainContext) {
		this.mainContext = mainContext;
	}
	
	public HttpServer createHttpServer(int port) {
		HttpServer httpServer = null;
		try {
			httpServer = new HttpServer(this.mainContext, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return httpServer;
	}
		
	public TcpServer createTcpServer(int port) {
		TcpServer tcpServer = null;
		try {
			tcpServer = new TcpServer(this.mainContext, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tcpServer;
	}
	
}