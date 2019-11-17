package newtime.net.http.control;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.view.View;

public class ExternalControllerManager implements ControllerManager {

	public View invoke(HttpConnection connection, HttpRequest request, String controller) {
		View view = null;
		
		String[] ops = controller.split("@");		
		File root = new File("http/controllers/");
		
		try {
			URL[] urls = new URL[] {root.toURI().toURL()};		
			ClassLoader loader = new URLClassLoader(urls);
			Class cls = loader.loadClass(ops[0]);
			Method m = cls.getMethod(ops[1], HttpConnection.class, HttpRequest.class);
			System.out.println(ops[1] + " " + m);
			view = (View) m.invoke(null, connection, request);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return view;
	}
	
}
