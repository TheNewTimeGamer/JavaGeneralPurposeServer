package newtime.net.http.response;

import java.util.Map;

import newtime.net.http.request.HttpRequest;

public class HttpResponse extends HttpRequest {

	public String status = "200 OK";
	
	public HttpResponse() {
		super();
	}
	
	public HttpResponse(String status) {
		super();
		this.status = status;
	}
	
	public HttpResponse(String status, String message) {
		super();
		this.status = status;
		this.body = message.getBytes();
		this.header.put("Content-Length", ""+body.length);
	}
	
	public HttpResponse(byte[] buffer) throws Exception {
		super(buffer);
	}

	public HttpResponse(HttpRequest request) {
		super();
		this.method = request.method;
		this.action = request.action;
		this.protocol = request.protocol;
		this.header = request.header;
		this.get = request.get;
		this.post = request.post;
		this.body = request.body;
	}
	
	protected String getProtocolAsString() {
		String protocol = this.protocol + " " + this.status + "\r\n";
		return protocol;
	}
	
}
