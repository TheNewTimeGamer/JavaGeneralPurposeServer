package newtime.net.http.control;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.HttpResponseNotFound;
import newtime.net.http.response.HttpResponseMethodNotAllowed;
import newtime.util.FileDictionary;
import newtime.util.ResourceManager;

public class GenericController implements Controller {
	
	public HttpResponse get(HttpConnection connection, HttpRequest request) {
		HttpResponse response = new HttpResponse();
		
		String path = request.action;
		
		if(!path.contains("\\.")) {
			path = path + "/index.html";
		}
		path = "http/public" + path;
			
		String fileExtension = FileDictionary.getFileExtensionFromPath(path);
		String contentType = FileDictionary.getMeta("http", fileExtension);
		
		byte[] data = ResourceManager.getFileContent(path);
		
		if(contentType.startsWith("text/")) {
			String raw = new String(data);
			data = raw.getBytes();
		}
		
		response.header.put("Content-Type", contentType);
		response.header.put("Content-Length", ""+data.length);
		
		response.body = data;
		return response;
	}	
	public HttpResponse post(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse head(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse put(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse delete(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse connect(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse options(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse trace(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	public HttpResponse patch(HttpConnection connection, HttpRequest request) {
		return new HttpResponseMethodNotAllowed();
	}
	
}