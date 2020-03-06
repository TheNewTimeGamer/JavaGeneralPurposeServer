package newtime.net.http.routing;

import java.io.File;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.resource.BinaryResource;
import newtime.net.http.resource.Resource;
import newtime.net.http.response.HttpResponse;
import newtime.util.FileDictionary;
import newtime.util.FileManager;

public class RouteManager {	
	
	public HttpResponse route(HttpConnection connection, HttpRequest request) {
		byte[] data = FileManager.getFileContent("http/routes.cfg");
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
				Resource resource = connection.getServerInstance().resources.loadResource("http/resources/private/", ops[1]);
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
			response = findInPublic(connection, request);
		}
		
		return response;
	}

	public HttpResponse findInPublic(HttpConnection connection, HttpRequest request) {
		File file = new File("http/resources/public/" + request.action);		
		Resource resource = new BinaryResource(file);
		
		if(resource.getContent() == null) {
			resource = connection.getServerInstance().resources.getResource("ResourceNotFound");
		}		
				
		return resource.build(null);		
	}

}
