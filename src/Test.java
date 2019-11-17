import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.view.View;

public class Test {

	public static View run(HttpConnection connection, HttpRequest request) {
		return connection.getServerInstance().views.get("test2");
	}
	
}
