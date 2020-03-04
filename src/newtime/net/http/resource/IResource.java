package newtime.net.http.resource;

import newtime.net.http.response.HttpResponse;
import newtime.net.instancing.Session;

public interface IResource {
		
	public String getContentType();
	public void setContentType(String contentType);
	
	public byte[] getContent();
	public void setContent(byte[] content);
	
	public abstract HttpResponse build(Session session);
	
}
