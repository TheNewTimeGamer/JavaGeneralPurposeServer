package newtime.net.http.routing;

import java.io.File;

import javax.swing.text.View;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.resource.Resource;
import newtime.net.http.resource.ResourceManager;
import newtime.net.http.response.HttpResponse;
import newtime.util.FileDictionary;
import newtime.util.FileManager;

public class RouteManager {

	public HttpResponse route(HttpConnection connection, HttpRequest request) {
		byte[] data = FileManager.getFileContent("http/routes/routes.cfg");
		if(data == null) {
			System.err.println("Could not load \"routes.cfg\"");
			return null;
		}
		String raw = new String(data);
		raw = raw.replace("\n", "");
		raw = raw.replace("\r", "");
		String[] lines = raw.split(";");
		
		HttpResponse response = null;
		
		for(String line : lines) {
			String[] args = line.split(",");
			if(!args[0].equals(request.action)) {continue;}
			String[] ops = args[1].split("::");
			if(ops[0].equals("view")) {
				Resource resource = connection.getServerInstance().resources.getResource(ops[1]);
				if(resource == null) {
					System.err.println("Unknown resource: " + ops[1]);
				}else {
					response = resource.build(null);
				}
			}else if (ops[0].equals("controller")) {
				response = connection.getServerInstance().controllers.invoke(connection, request, ops[1]);
			}
		}
		
		if(response == null) {
			response = findInDefaults(request.action);
		}
		
		return response;
	}

	public HttpResponse findInDefaults(String action) {
		File file = new File("http/defaults/" + action);
		
		byte[] buffer = FileManager.getFileContent(file);
		
		HttpResponse response = new HttpResponse();
		response.header.put("Content-Type", FileDictionary.getMeta("http", FileDictionary.getFileExtensionFromPath(file.getAbsolutePath())));		
		response.header.put("Content-Length", ""+buffer.length);
		response.body = buffer;

		return response;		
	}

}
