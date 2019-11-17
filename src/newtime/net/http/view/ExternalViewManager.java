package newtime.net.http.view;

import java.io.File;

public class ExternalViewManager extends ViewManager {

	public View get(String key) {
		File root = new File("http/views");
		File[] files = root.listFiles();
		
		View view = null;
		
		for(File file : files) {
			if(!file.getName().split("\\.")[0].equals(key)) {continue;}
			view = new View(file.getAbsolutePath());
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
