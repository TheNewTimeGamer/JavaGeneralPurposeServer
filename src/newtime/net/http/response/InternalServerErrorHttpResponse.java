package newtime.net.http.response;

public class InternalServerErrorHttpResponse extends HttpResponse {

	public InternalServerErrorHttpResponse() {
		this.status = "501 Method Not Implemented";
		this.body = "501 Method Not Implemented".getBytes();
		this.header.put("Content-Length", ""+this.body.length);
	}
	
}
