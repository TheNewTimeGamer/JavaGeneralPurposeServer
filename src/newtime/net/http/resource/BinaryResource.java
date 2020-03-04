package newtime.net.http.resource;

import java.io.File;

import newtime.net.http.response.HttpResponse;
import newtime.net.instancing.Session;
import newtime.util.FileManager;

public class BinaryResource extends Resource{
	
	protected byte[] content;
	
	public BinaryResource() {}
	
	public BinaryResource(String contentType, File file) {
		this.contentType = contentType;
		this.content = FileManager.getFileContent(file);
	}
	
	public BinaryResource(String contentType, byte[] content) {
		this.contentType = contentType;
		this.content = content;
	}
		
	public byte[] getContent() {
		return this.content;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}

	public HttpResponse build(Session session) {
		HttpResponse response = new HttpResponse();
		
		response.status = "200 OK";		
		response.body = this.getContent();
		
		response.header.put("Content-Type", this.contentType);
		response.header.put("Content-Length", ""+response.body.length);
		
		return response;
	}
	

}
