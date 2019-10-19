package newtime.net.http.response;

public class HttpResponseMethodNotImplemented extends HttpResponse {

	public HttpResponseMethodNotImplemented() {
		this.status = "501 Method Not Implemented";
		this.body = "<b>501<br>Method Not Implemented!</b>";
		this.header.put("Content-Type", "text/html");
		this.header.put("Content-Length", ""+this.body.length());
	}

}
