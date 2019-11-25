package newtime.net.http.view;

import java.io.File;

import newtime.maniac.Preprocessor;
import newtime.net.http.response.HttpResponse;
import newtime.net.instancing.Session;
import newtime.util.ResourceManager;

public class View {
	
	private String status = "200 OK";
	private String content = "";
	
	public View(String status, String content) {
		this.status = status;
		this.content = content;
	}
	
	public View(File file) {
		byte[] data = ResourceManager.getFileContent(file);
		if(data == null) {
			System.err.println("Could not load view template from: " + file.getAbsolutePath());
		}
		this.content = new String(data);
	}
	
	public HttpResponse build() {
		HttpResponse response = new HttpResponse();
		
		response.body = content.getBytes();
		
		response.header.put("Content-Type", "text/html");
		response.header.put("Content-Length", ""+response.body.length);
		
		return response;
	}
	
	public HttpResponse build(Session session) {
		HttpResponse response = new HttpResponse();
		
		response.status = this.status;
		
		response.body = Preprocessor.process(session, this.content).getBytes();
		
		response.header.put("Content-Type", "text/html");
		response.header.put("Content-Length", ""+response.body.length);
		
		return response;
	}
	
}
