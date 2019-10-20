package newtime.net.http.response;

public class HttpResponseMethodNotAllowed extends HttpResponse {

	public HttpResponseMethodNotAllowed() {
		this.status = "405 Method Not Allowed";
		this.body = "<b>405<br>Method Not Allowed!</b>".getBytes();
		this.header.put("Content-Type", "text/html");
		this.header.put("Content-Length", ""+this.body.length);
	}

}
