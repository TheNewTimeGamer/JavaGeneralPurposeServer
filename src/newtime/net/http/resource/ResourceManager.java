package newtime.net.http.resource;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

import newtime.net.http.response.HttpResponse;
import newtime.net.instancing.Session;
import newtime.util.FileDictionary;
import newtime.util.FileManager;

public class ResourceManager {
	
	private HashMap<String, Resource> resources = new HashMap<String, Resource>();

	public Resource loadResource(String rootPath, String key) {
		Resource resource = null;
		try {
			File root = new File(rootPath);		
			URL[] url = new URL[] { root.toURI().toURL() };
			
			ClassLoader loader = new URLClassLoader(url);
			Class c = loader.loadClass(key);
			
			resource = (Resource) c.newInstance();
		}catch(Exception e) {
			if(e.getCause() != null) {
				e.initCause(e.getCause()).printStackTrace();
			}else {
				e.printStackTrace();
			}
		}
		return resource;	
	}
	
	public HttpResponse buildResource(String key, Session session) {
		HttpResponse response = null;
		Resource resource = loadResource("http/resources/private/", key);
		response = resource.build(session);
		return response;
	}

	public void loadDefaults() {
		System.out.println("Loading defaults..");
		File[] files = new File("http/resources/default/").listFiles();
		for(File f : files) {
			System.out.print(" - " + f.getName() + " ..");
			Resource resource = loadResource("http/resources/default/", f.getName().split("\\.")[0]);
			if(resource != null) {
				System.out.println(" Success!");
				storeResource(f.getName().split("\\.")[0], resource);
			}else {
				System.out.println(" Failed!");
			}
		}
	}
	
	public BinaryResource storeBinaryResource(String name, String path) {
		return storeBinaryResource(name, new File(path));
	}
	
	public BinaryResource storeBinaryResource(String name, File file) {
		byte[] buffer = FileManager.getFileContent(file);
		if(buffer == null) {return null;}
		
		String contentType = FileDictionary.getFileExtensionFromPath(file.getAbsolutePath());
		BinaryResource resource = new BinaryResource(contentType, buffer);
		
		return resource;
	}
	
	public Resource storeHtmlResource(String name, HtmlResource resource) {
		return resources.put(name, resource);	
	}
	
	public Resource storeResource(String name, Resource resource) {
		return resources.put(name, resource);
	}
	
	public Resource getResource(String name) {
		Resource resource = resources.get(name);
		return resource;
	}
	
	public Resource releaseResource(String name) {
		Resource resource = resources.remove(name);
		return resource;
	}

	
}
