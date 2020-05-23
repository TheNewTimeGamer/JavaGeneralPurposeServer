package newtime.net.http.response;

public class MethodNotImplementedHttpResponse extends HttpResponse {

	public MethodNotImplementedHttpResponse() {
		this.status = "500 Internal Server Error";
		this.body = "500 Internal Server Error".getBytes();
		this.header.put("Content-Length", ""+this.body.length);
	}
	
}
