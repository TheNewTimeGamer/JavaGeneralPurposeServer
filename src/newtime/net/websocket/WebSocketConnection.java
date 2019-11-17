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

	protected void onData(byte[] data) {
		
		
		HttpResponse response = null;
		// First we must read the HTTP handshake.
		String sData = new String(data, StandardCharsets.UTF_8);
		String args[] = sData.split(" ", 2);
		// Check if the incoming request is a GET request
		if(args[0].equals("GET")) {
			
			HttpRequest request = HttpRequest.build(data);
			boolean correctHandshake = true;
			
//			if(request == null) {
//				response = new HttpResponseBadRequest();
//				correctHandshake = false;
//			}
			
			// Checking if the request is a correct websocket handshake. See rfc6455 4.2.1 for more details
			String protocol[] = request.protocol.split("/");			
			if(protocol[0] != "HTTP" && Double.parseDouble(protocol[1]) < 1.1 ) {
				correctHandshake = false;
			}
			else if(request.header.get("Host") == null || request.header.get("Host").equals("")) {
				correctHandshake = false;
			}
			else if(request.header.get("Origin") == null || request.header.get("Origin").equals("")) {
				correctHandshake = false;
			}
			else if(!request.header.get("Upgrade").equals("websocket")) {
				correctHandshake = false;
			}
			else if(!request.header.get("Sec-WebSocket-Version").equals("13")) {
				correctHandshake = false;
			}
			else if(request.header.get("Sec-WebSocket-Key") == null || request.header.get("Sec-WebSocket-Key") == null) {
				correctHandshake = false;
			}
			//Optional parts 8 and 9 from rfc6455 4.2.1 are not checked for now.
			
			// If the handshake is correct up to this point, we can check and decode the base 64 key, then create the response key.
			String responseKey = null;
			if(correctHandshake == true) {
				
				String key = request.header.get("Sec-WebSocket-Key");
				key = key.trim();
				key = key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
				
				try {
					responseKey = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-1").digest(key.getBytes(StandardCharsets.UTF_8)));
				} catch (NoSuchAlgorithmException e) {
					correctHandshake = false;
					e.printStackTrace();
				}
			}
			
			
			if(correctHandshake == true) {
				System.out.println("true");
				response = new HttpResponseSwitchingProtocols(responseKey);
			} else {
				System.out.println("False");
				response = new HttpResponseBadRequest();
			}
			
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
