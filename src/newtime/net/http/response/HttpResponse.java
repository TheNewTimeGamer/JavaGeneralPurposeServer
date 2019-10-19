package newtime.net.http.response;

import newtime.net.http.request.HttpRequest;

public class HttpResponse extends HttpRequest {

	public String status = "200 OK";
	
	public HttpResponse() {
		super();
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
	
	public String toString() {
		String string = super.toString();
		String[] lines = string.split("\r\n", 2);
		lines[0] = this.protocol + " " + status;
		return lines[0] + "\r\n" + lines[1];
	}
	
}
