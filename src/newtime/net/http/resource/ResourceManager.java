package newtime.net.http.resource;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import newtime.net.http.response.HttpResponse;
import newtime.net.instancing.Session;

public class ResourceManager {

	public Resource getResource(String key) {
		Resource resource = null;
		try {
			File root = new File("http/resources/");		
			URL[] url = new URL[] { root.toURI().toURL() };
			
			ClassLoader loader = new URLClassLoader(url);
			Class c = loader.loadClass(key);
			
			resource = (Resource) c.newInstance();
		}catch(Exception e) {
			e.initCause(e.getCause()).printStackTrace();
		}
		return resource;	
	}
	
	public HttpResponse buildResource(String key, Session session) {
		HttpResponse response = null;
		Resource resource = getResource(key);
		response = resource.build(session);
		return response;
	}

}
