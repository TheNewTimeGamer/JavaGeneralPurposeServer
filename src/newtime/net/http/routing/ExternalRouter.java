package newtime.net.http.routing;

import newtime.net.http.HttpConnection;
import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.http.view.View;
import newtime.util.ResourceManager;

public class ExternalRouter extends Router {

	public HttpResponse route(HttpConnection connection, HttpRequest request) {
		byte[] data = ResourceManager.getFileContent("http/routes/routes.cfg");
		if(data == null) {
			System.err.println("Could not load \"routes.cfg\"");
			return null;
		}
		String raw = new String(data);
		raw = raw.replace("\n", "");
		raw = raw.replace("\r", "");
		String[] lines = raw.split(";");
		
		HttpResponse response = null;
		
		for(String line : lines) {
			String[] args = line.split(",");
			if(!args[0].equals(request.action)) {continue;}
			String[] ops = args[1].split("::");
			if(ops[0].equals("view")) {
				View view = connection.getServerInstance().views.get(ops[1]);
				if(view == null) {
					System.out.println("Unknown View: " + ops[1]);
				}else {
					response = view.build();
				}
			}else if (ops[0].equals("controller")) {
				response = connection.getServerInstance().controllers.invoke(connection, request, ops[1]);
			}
		}
		
		return response;
	}

	public Route createRoute(String action) {
		throw new UnsupportedOperationException("The external Router does not support this feature.");
	}
	
	public Route createRoute(String action, Method method) {
		throw new UnsupportedOperationException("The external Router does not support this feature.");
	}

}
