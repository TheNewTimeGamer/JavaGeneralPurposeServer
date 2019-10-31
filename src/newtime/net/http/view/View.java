package newtime.net.http.view;

import newtime.net.auth.Session;
import newtime.net.http.response.HttpResponse;

public abstract class View {
	
	public abstract HttpResponse build(Session session);
	
}
