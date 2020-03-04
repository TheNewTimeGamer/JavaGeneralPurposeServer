import java.io.File;

import org.jsoup.Jsoup;

import newtime.net.http.resource.HtmlResource;
import newtime.net.http.response.HttpResponse;
import newtime.net.instancing.Session;
import newtime.util.FileManager;

public class TestResponse extends HtmlResource {
		
	public TestResponse() {
		super(new File("http/html/home.html"));
	}

	public HttpResponse build(Session session) {
		HttpResponse response = new HttpResponse();
		
		response.body = this.document.toString().getBytes();
		
		response.header.put("Content-Type", "text/html");
		response.header.put("Content-Length", ""+response.body.length);
		
		return response;
	}

	
	
}
