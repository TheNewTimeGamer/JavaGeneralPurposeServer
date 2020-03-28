package newtime.net.http.control;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.HttpResponseInternalServerError;

public class ControllerManager {

	public HttpResponse invoke(HttpConnection connection, HttpRequest request, String controllerName) {
		HttpResponse response = null;
		
		String[] ops = controllerName.split("@");		
		File root = new File("http/controllers/");
		try {
			URL[] urls = new URL[] {root.toURI().toURL()};		
			ClassLoader loader = new URLClassLoader(urls);
			Class cls = loader.loadClass(ops[0]);
			
			Controller controller = (Controller) cls.newInstance();
			
			if(cls == null) { System.err.println("No such class: \"" + ops[0] + "\"!"); }
			Method m = cls.getMethod(ops[1], HttpConnection.class, HttpRequest.class);
						
			if(m == null) { System.err.println("No such method: \"" + ops[1] + "\" within controller: \"" + ops[0] + "\"!"); }
			
			response = (HttpResponse) m.invoke(controller, connection, request);
		}catch(Exception e) {
			System.err.println("Could not invoke method: \"" + ops[1] + "\" within controller: \"" + ops[0] + "\"!");
			if(e.getCause() != null) {
				e.getCause().printStackTrace();
			}else {
				e.printStackTrace();
			}
			response = new HttpResponseInternalServerError();
		}
		return response;
	}
	
}
