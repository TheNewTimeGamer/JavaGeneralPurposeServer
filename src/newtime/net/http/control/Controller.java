package newtime.net.http.control;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;

public class Controller {

	public HttpResponse test(HttpConnection connection, HttpRequest request) {
		return connection.getServerInstance().views.get("test").build();
	}
	
}
