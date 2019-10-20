package newtime.net.http.response;

public class HttpResponseBadRequest extends HttpResponse {

	public HttpResponseBadRequest() {
		this.status = "400 Bad Request";
		this.body = "<b>400<br>Bad Request!</b>".getBytes();
		this.header.put("Content-Type", "text/html");
		this.header.put("Content-Length", ""+this.body.length);
	}

}