import newtime.net.http.HttpConnection;
import newtime.net.http.control.Controller;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.view.View;
import newtime.net.http.view.ViewManager;	

public class HomeController extends Controller {

	public static HttpResponse showHomePage(HttpConnection connection, HttpRequest request) {
		View view = connection.getServerInstance().views.get("home");
		if(view == null) {
			return ViewManager.FILE_NOT_FOUND.build();
		}
		return view.build();
	}	
	
}
