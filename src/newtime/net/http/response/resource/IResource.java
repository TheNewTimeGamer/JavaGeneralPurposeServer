package newtime.net.http.response.resource;

import newtime.net.http.response.HttpResponse;

public interface IResource {
		
	public String getContentType();
	public void setContentType(String contentType);
	
	public byte[] getContent();
	public void setContent(byte[] content);
	
	public abstract HttpResponse build();
	
}
