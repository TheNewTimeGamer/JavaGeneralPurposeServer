package newtime.net.websocket;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.HttpResponseBadRequest;
import newtime.net.http.response.HttpResponseSwitchingProtocols;
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

	WebSocketWorker worker = new WebSocketWorker();
	
	protected void onData(byte[] data) {
		
		
		HttpResponse response = null;
		// First we must read the HTTP handshake.
		String sData = new String(data, StandardCharsets.UTF_8);
		String args[] = sData.split(" ", 2);
		// Check if the incoming request is a GET request
		if(args[0].equals("GET")) {
			response = worker.Handshake(data);
		} else {
			response = new HttpResponseBadRequest();
		}
		
		// Send the response back to the client.
		try {
			out.write(response.toString().getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	

	
}
