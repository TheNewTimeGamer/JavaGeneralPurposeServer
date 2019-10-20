package newtime.net.http.response;

public class HttpResponseNotFound extends HttpResponse {

	public HttpResponseNotFound() {
		this.status = "404 Not Found";
		this.body = "<b>404<br>Not Found!</b>".getBytes();
		this.header.put("Content-Type", "text/html");
		this.header.put("Content-Length", ""+this.body.length);
	}
	
}
