package newtime.net.http.resource;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import newtime.net.http.response.HttpResponse;
import newtime.net.instancing.Session;
import newtime.util.FileManager;

public abstract class HtmlResource extends Resource {
	
	// TODO: Look into overriding contentType by default. (Override class variable)
	
	protected String status;
	protected Document document;
	
	public HtmlResource() {}
	
	public HtmlResource(File file) {
		String content = new String(FileManager.getFileContent(file));
		this.contentType = "text/html";
		this.document = Jsoup.parse(content);
	}
	
	public HtmlResource(String documentName) {
		this.contentType = "text/html";
		this.document = new Document(documentName);
	}
	
	public HtmlResource(Document document) {
		this.contentType = "text/html";
		this.document = document;
	}
		
	public byte[] getContent() {
		return document.toString().getBytes();
	}

	public void setContent(byte[] content) {
		this.document = Jsoup.parse(new String(content));
	}

	public void setContent(String content) {
		this.document = Jsoup.parse(content);
	}
	
	public void setContent(Document document) {
		this.document = document;
	}
	
	public abstract HttpResponse build(Session session);

}
