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
		while(path.contains("..")) {
			path = path.replace("..", "");
		}
		
		if(path.length() <= 1) {
			path = "\\index.html";
		}
		
		path = "http\\public" + path;
		
		byte[] data = ResourceManager.getFileContent(path);
		if(data == null) {
			return new HttpResponseNotFound();
		}		
		
		response.body = data;
		response.header.put("Content-Length", ""+response.body.length);
		
		String[] extension = path.split("\\.");
		
		String contentType = FileDictionary.getMeta("http", extension[extension.length-1]);
		if(contentType == null) {
			contentType = "text/html";
		}		
		
		response.header.put("Content-Type", contentType);
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