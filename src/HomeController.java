import newtime.net.auth.Session;
import newtime.net.http.HttpConnection;
import newtime.net.http.control.Controller;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;

public class HomeController extends Controller {

	public static HttpResponse displayList(HttpConnection connection, HttpRequest request) {
		Session session = new Session();
		session.variables.put("test", "Tim");
		HttpResponse response = connection.getServerInstance().views.get("test2").build(session);		
		return response;
	}
	
}