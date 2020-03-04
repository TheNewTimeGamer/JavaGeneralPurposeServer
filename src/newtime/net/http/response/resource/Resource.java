package newtime.net.http.response.resource;

import newtime.net.http.response.HttpResponse;

public abstract class Resource implements IResource {

	protected String status;
	protected String contentType;

	public String getContentType() {
		return this.contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public abstract byte[] getContent();

	
	public abstract void setContent(byte[] content);

	
	public abstract HttpResponse build();
	
}
