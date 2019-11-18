package newtime.net.websocket;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.HttpResponseBadRequest;
import newtime.net.http.response.HttpResponseSwitchingProtocols;

public class WebSocketWorker {
	
	WebSocketWorker(){
		
	}
	
	//
	
	
	protected HttpResponse Handshake(byte[] data) {
		System.out.println("Incoming get request");
		HttpRequest request = HttpRequest.build(data);
		boolean correctHandshake = true;
		
//		if(request == null) {
//			response = new HttpResponseBadRequest();
//			correctHandshake = false;
//		}
		
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
			// OPTIONAL TODO: Send a 426 Upgrade Required response. 
			// With a |Sec-WebSocket-Version| header field indicating the version(s) the server is capable of understanding.
			// See: RFC 6455 4.2.2
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
			return new HttpResponseSwitchingProtocols(responseKey);
			// the protocol has been switched to the websocket protocol.
		} else {
			System.out.println("False");
			return new HttpResponseBadRequest();
		}
		
	}
	
	
	
	
	

}
