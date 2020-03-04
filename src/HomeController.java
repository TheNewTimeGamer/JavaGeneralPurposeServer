import newtime.net.http.HttpConnection;
import newtime.net.http.control.Controller;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.resource.Resource;
import newtime.net.http.response.HttpResponse;	

public class HomeController extends Controller {

	public HttpResponse showHomePage(HttpConnection connection, HttpRequest request) {
		Resource resource = connection.getServerInstance().resources.getResource("TestResponse");
		return resource.build(null);
	}	
	
}
