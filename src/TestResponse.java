import org.jsoup.Jsoup;

import newtime.net.http.resource.HtmlResource;
import newtime.net.http.response.HttpResponse;
import newtime.net.instancing.Session;

public class TestResponse extends HtmlResource {
		
	public TestResponse() {
		super("");
		this.document = Jsoup.parse("<html>Test <b>Bold</b></html>");
	}

	public HttpResponse build(Session session) {
		HttpResponse response = new HttpResponse();
		
		response.body = this.document.toString().getBytes();
		
		response.header.put("Content-Type", "text/html");
		response.header.put("Content-Length", ""+response.body.length);
		
		return response;
	}

	
	
}
