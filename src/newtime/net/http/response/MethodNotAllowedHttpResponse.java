package newtime.net.http.response;

public class MethodNotAllowedHttpResponse extends HttpResponse {

	public MethodNotAllowedHttpResponse() {
		this.status = "405 Method Not Allowed";
		this.body = "405 Method Not Allowed".getBytes();
		this.header.put("Content-Length", ""+this.body.length);
	}
	
}
