package newtime.net.http.resource;

import java.io.File;

import newtime.net.http.response.resource.BinaryResource;
import newtime.net.http.response.resource.Resource;
import newtime.util.FileDictionary;
import newtime.util.FileManager;

public class ResourceManager implements IResourceManager {

	public Resource get(String key) {
		File root = new File("http/views");
		File[] files = root.listFiles();
		
		BinaryResource resource = null;
		
		for(File file : files) {
			if(!file.getName().split("\\.")[0].equals(key)) {continue;}
			byte[] content = FileManager.getFileContent(file);
			resource = new BinaryResource(FileDictionary.getMeta("http", FileDictionary.getFileExtensionFromPath(file.getAbsolutePath())), content);
		}
		return resource;
	}

	public Resource put(String key, Resource view) {
		throw new UnsupportedOperationException("The external ViewManager does not support this feature.");
	}

	public Resource create(String key, String path) {
		throw new UnsupportedOperationException("The external ViewManager does not support this feature.");
	}

}
