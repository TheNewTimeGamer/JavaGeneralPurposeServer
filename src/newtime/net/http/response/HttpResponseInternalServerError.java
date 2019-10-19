package newtime.net.http.response;

public class HttpResponseInternalServerError extends HttpResponse {

	public HttpResponseInternalServerError() {
		this.status = "500 Internal Server Error";
		this.body = "<b>500<br>Internal Server Error!</b>";
		this.header.put("Content-Type", "text/html");
		this.header.put("Content-Length", ""+this.body.length());
	}
	
}
