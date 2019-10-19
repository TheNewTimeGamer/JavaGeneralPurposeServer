package newtime.net.http.request;

import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

	public static HttpRequest build(byte[] buffer) {
		HttpRequest request = null;
		try {
			request = new HttpRequest(buffer);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return request;
	}
	
	public String method;
	public String action;
	public String protocol = "HTTP/1.1";
	
	public HashMap<String, String> header = new HashMap<String, String>();
	public HashMap<String, String> get = new HashMap<String, String>();
	public HashMap<String, String> post = new HashMap<String, String>();
	
	public String body;
	
	public HttpRequest() {}
	
	public HttpRequest(byte[] buffer) throws Exception {
		String[] requestParts = new String(buffer).split("\r\n\r\n");				
		String[] lines = requestParts[0].split("\r\n");
		
		processProtocol(lines);
		processHeaders(lines);
		processGetParameters(lines);
		
		if(requestParts.length > 1) {
			processPostParameters(requestParts[1]);
		}
	}
	
	protected void processProtocol(String[] lines) {
		String[] args = lines[0].split(" ");
		
		this.method = args[0];
		this.action = args[1];
		this.protocol = args[2];
	}

	protected void processHeaders(String[] lines) {
		for(int i = 1; i < lines.length; i++) {
			String[] args = lines[i].split(": ");
			header.put(args[0], args[1]);
			if(lines[i].equals("\r\n")) {break;}
		}
	}
	
	protected void processGetParameters(String[] lines) {
		if(action.contains("?")) {
			String[] parts = action.split("\\?");
			action = parts[0];
			if(parts[1].contains("&")) {
				String[] parameters = parts[1].split("&");
				for(int i = 0; i < parameters.length; i++) {
					String[] args = parameters[i].split("=");
					get.put(args[0], args[1]);
				}
				
			}else if(parts[1].contains("=")){
				String[] args = parts[1].split("=");
				get.put(args[0], args[1]);
			}
		}
	}
	
	protected void processPostParameters(String body) {
		String contentType = header.get("Content-Type");
		if(contentType == null || contentType.contains("text/html")) {		
			if(body.contains("&")) {
				String[] parameters = body.split("&");
				for(int i = 0; i < parameters.length; i++) {
					if(parameters[i].contains("=")) {
						String[] args = parameters[i].split("=");
						post.put(args[0], args[1]);
					}
				}
			}else if(body.contains("=")) {
				String[] args = body.split("=");
				post.put(args[0], args[1]);
			}else {
				this.body = "";
			}
		}else {
			this.body = body;
		}
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		StringBuilder actionBuilder = new StringBuilder();
		
		if(get.size() > 0) {
			actionBuilder.append(action + "?");
			for(Map.Entry<String, String> e : get.entrySet()) {
				actionBuilder.append(e.getKey() + "=" + e.getValue() + "&");
			}
			actionBuilder.deleteCharAt(actionBuilder.length()-1);
		}else {
			actionBuilder.append(action);
		}
		
		builder.append(method + " " + actionBuilder.toString() + " " + protocol + "\r\n");
		
		for(Map.Entry<String, String> e : header.entrySet()) {
			builder.append(e.getKey() + ": " + e.getValue() + "\r\n");
		}
		builder.append("\r\n");
		
		if(this.body != null) {
			builder.append(this.body);
		}else {
			for(Map.Entry<String, String> e : post.entrySet()) {
				builder.append(e.getKey() + "=" + e.getValue() + "&");
			}
			builder.deleteCharAt(builder.length()-1);
		}
		
		return builder.toString();
	}
	
}
