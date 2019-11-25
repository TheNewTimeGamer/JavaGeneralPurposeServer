package newtime.net.http.view;

import java.io.File;

import newtime.maniac.Preprocessor;
import newtime.net.http.response.HttpResponse;
import newtime.net.instancing.Session;
import newtime.util.FileDictionary;
import newtime.util.ResourceManager;

public class View {
	
	private String status = "200 OK";
	private String content = "";
	
	private String contentType = "text/html";
	
	public View(String status, String contentType, String content) {
		this.status = status;
		this.content = content;
	}
	
	public View(File file) {
		byte[] data = ResourceManager.getFileContent(file);
		
		String extension = FileDictionary.getFileExtensionFromPath(file.getAbsolutePath());
		String cType = FileDictionary.getMeta("http", extension);
		if(cType != null) {
			this.contentType = cType;
		}
		
		if(data == null) {
			System.err.println("Could not load view template from: " + file.getAbsolutePath());
		}
		this.content = new String(data);
	}
	
	public HttpResponse build() {
		HttpResponse response = new HttpResponse();
		
		response.body = content.getBytes();
		
		response.header.put("Content-Type", this.contentType);
		response.header.put("Content-Length", ""+response.body.length);
		
		return response;
	}
	
	public HttpResponse build(Session session) {
		HttpResponse response = new HttpResponse();
		
		response.status = this.status;
		
		response.body = Preprocessor.process(session, this.content).getBytes();
		
		response.header.put("Content-Type", this.contentType);
		response.header.put("Content-Length", ""+response.body.length);
		
		return response;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
