package newtime.net.http.response.resource;

import newtime.net.http.response.HttpResponse;

public class BinaryResource extends Resource{
	
	protected byte[] content;
	
	public BinaryResource() {}
	
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
	
	public HttpResponse build() {
		HttpResponse response = new HttpResponse();
		
		response.status = "200 OK";		
		response.body = this.getContent();
		
		response.header.put("Content-Type", this.contentType);
		response.header.put("Content-Length", ""+response.body.length);
		
		return response;
	}
	
}
