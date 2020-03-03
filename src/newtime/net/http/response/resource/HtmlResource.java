package newtime.net.http.response.resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import newtime.net.http.response.HttpResponse;

public abstract class HtmlResource extends Resource {
	
	protected String status;
	protected Document document;
	
	public HtmlResource(String documentName) {
		this.document = new Document(documentName);
	}
	
	public HtmlResource(Document document) {
		this.document = document;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
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
	
	public abstract HttpResponse build();

}
