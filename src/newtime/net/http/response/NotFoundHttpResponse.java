package newtime.net.http.response;

public class NotFoundHttpResponse extends HttpResponse {

	public NotFoundHttpResponse() {
		this.status = "404 Not Found";
		this.body = "404 Not Found".getBytes();
		this.header.put("Content-Length", ""+this.body.length);
	}
	
}
