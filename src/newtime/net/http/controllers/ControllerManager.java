package newtime.net.http.controllers;

import java.util.ArrayList;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.NotFoundHttpResponse;
import newtime.util.FileDictionary;
import newtime.util.FileManager;

public class ControllerManager {

	public Controller defaultController = new DefaultController();
	public ArrayList<Controller> controllers = new ArrayList<Controller>();
	
}

class DefaultController extends Controller {
	
	public HttpResponse get(HttpConnection connection, HttpRequest request) {	
		String path = "http/public/" + request.action;
		byte[] data = FileManager.getFileContent(path);
		if(data == null) {		
			return new NotFoundHttpResponse();
		}
		
		String extension = FileDictionary.getFileExtensionFromPath(path);
		String contentType = FileDictionary.getMeta("http", extension);
		
		HttpResponse response = new HttpResponse();
		
		response.body = data;
		response.header.put("Content-Length", ""+response.body.length);
		if(contentType != null) {
			response.header.put("Content-Type", contentType);
		}
		
		return response;
	}
	
}
