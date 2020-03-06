import java.io.File;

import newtime.net.http.resource.HtmlResource;
import newtime.net.http.response.HttpResponse;
import newtime.net.instancing.Session;

public class ResourceNotFound extends HtmlResource {

	public ResourceNotFound() {
		super(new File("http/resources/templates/default/FileNotFound.html"));
	}
	
	public HttpResponse build(Session session) {
		HttpResponse response = new HttpResponse();
		
		response.body = this.getContent();
		
		response.header.put("Content-Length", ""+response.body.length);
		response.header.put("Content-Type", this.contentType);
				
		return response;
	}

}
