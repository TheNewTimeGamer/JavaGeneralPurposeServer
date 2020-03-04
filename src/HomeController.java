import newtime.net.http.HttpConnection;
import newtime.net.http.control.Controller;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.resource.BinaryResource;	

public class HomeController extends Controller {

	public static HttpResponse showHomePage(HttpConnection connection, HttpRequest request) {
		BinaryResource resource = (BinaryResource) connection.getServerInstance().resources.get("home");
		return resource.build();
	}	
	
}
