package newtime.net.http.response;

public class HttpResponseTeapot extends HttpResponse {

	public HttpResponseTeapot() {
		this.status = "418 I'm a teapot";
		this.body = "<b>Teapot</b>".getBytes();
		this.header.put("Content-Type", "text/html");
		this.header.put("Content-Length", ""+this.body.length);
	}
	
}
