package newtime.net.http.view;

import java.io.File;

public class ExternalViewManager extends ViewManager {

	public static final View FILE_NOT_FOUND = new View(new File("http/defaults/FileNotFound.html"));
	public static final View METHOD_NOT_ALLOWED = new View(new File("http/defaults/MethodNotAllowed.html"));
	public static final View METHOD_NOT_IMPLEMENTED = new View(new File("http/defaults/MethodNotImplemented.html"));
	
	public View get(String key) {
		File root = new File("http/views");
		File[] files = root.listFiles();
		
		View view = null;
		
		for(File file : files) {
			if(!file.getName().split("\\.")[0].equals(key)) {continue;}
			view = new View(file);
		}
		return view;
	}

	public View put(String key, View view) {
		throw new UnsupportedOperationException("The external Router does not support this feature.");
	}

	public View create(String key, String path) {
		throw new UnsupportedOperationException("The external Router does not support this feature.");
	}

}
