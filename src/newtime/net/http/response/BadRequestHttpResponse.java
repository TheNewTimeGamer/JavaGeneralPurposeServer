package newtime.net.http.response;

public class BadRequestHttpResponse extends HttpResponse {
	
	public BadRequestHttpResponse() {
		this.status = "400 Bad Request";
		this.body = "400 Bad Request".getBytes();
		this.header.put("Content-Length", ""+this.body.length);
	}
	
}
