import newtime.net.http.HttpConnection;
import newtime.net.http.control.Controller;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.instancing.Session;

public class HomeController extends Controller {

	public static HttpResponse showHomePage(HttpConnection connection, HttpRequest request) {
		Session session = new Session();
		session.variables.put("test", "Tim");
		HttpResponse response = connection.getServerInstance().views.get("home").build(session);		
		return response;
	}	
	
}
