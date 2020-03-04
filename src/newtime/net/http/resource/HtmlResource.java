package newtime.net.http.resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import newtime.net.http.response.HttpResponse;
import newtime.net.instancing.Session;

public abstract class HtmlResource extends Resource {
	
	protected String status;
	protected Document document;
	
	public HtmlResource(String documentName) {
		this.document = new Document(documentName);
	}
	
	public HtmlResource(Document document) {
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
