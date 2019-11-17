package newtime.net.http.response;

public class HttpResponseInternalServerError extends HttpResponse {

	public HttpResponseInternalServerError() {
		super("500 Internal Server Error", "<b>500 Internal Server Error</b>");
	}
	
}
