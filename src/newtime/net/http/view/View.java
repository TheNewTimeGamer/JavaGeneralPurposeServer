package newtime.net.http.view;

import java.io.File;

import newtime.net.http.response.HttpResponse;
import newtime.net.http.response.resource.Resource;
import newtime.util.FileDictionary;
import newtime.util.ResourceManager;

public class View extends Resource {
	
	private String status = "200 OK";
		
	public View(String status, String contentType, String content) {
		super(contentType, content.getBytes());
		this.status = status;
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
		this.content = data;
	}
	
	public HttpResponse build() {
		HttpResponse response = new HttpResponse();
		
		response.status = status;
		
		response.body = this.content;
		
		response.header.put("Content-Type", this.contentType);
		response.header.put("Content-Length", ""+response.body.length);
		
		return response;
	}
	
	public byte[] getContent() {
		return this.content;
	}
	
	public String getContentAsString() {
		return new String(this.content);
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public void setContent(String content) {
		this.content = content.getBytes();
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
