package newtime.net.http.control;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.HttpResponseInternalServerError;
import newtime.net.http.view.View;

public class ExternalControllerManager extends ControllerManager {

	public HttpResponse invoke(HttpConnection connection, HttpRequest request, String controller) {
		HttpResponse response = null;
		
		String[] ops = controller.split("@");		
		File root = new File("http/controllers/");
		
		try {
			URL[] urls = new URL[] {root.toURI().toURL()};		
			ClassLoader loader = new URLClassLoader(urls);
			Class cls = loader.loadClass(ops[0]);
			Method m = cls.getMethod(ops[1], HttpConnection.class, HttpRequest.class);
			response = (HttpResponse) m.invoke(null, connection, request);
		}catch(Exception e) {
			e.printStackTrace();
			response = new HttpResponseInternalServerError();
		}
		return response;
	}
	
}
