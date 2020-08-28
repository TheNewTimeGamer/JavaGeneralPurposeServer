package newtime.net.http;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import newtime.net.http.request.HttpRequest;
import newtime.net.http.response.HttpResponse;
import newtime.net.tcp.TcpServer;
import newtime.util.FileManager;

public class HttpServer extends TcpServer {

	public HashMap<String, String> routes = new HashMap<String, String>();

	public HttpServer(Context mainContext, int port) throws IOException {
		super(mainContext, port);
	}

	public HttpConnection onConnection(Socket socket) {
		HttpConnection connection = null;
		try {
			connection = new HttpConnection(this, socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void registerRoute(String action, Value page) {
		routes.put(action, page.toString());
	}

	public HttpResponse route(HttpConnection connection, HttpRequest request) {	
		String page = routes.get(request.action);
		if(page != null) {
			Context localContext = Context.newBuilder("js").option("js.strict", "true").allowAllAccess(true).build();
			
			String source = new String(FileManager.getFileContent("http/" + page + "/" + page + ".js"));
			Value eval = localContext.eval("js", source);

			Value value = localContext.getBindings("js").getMember(page);
			Document document = this.getDocument(value, "http/" + page + "/" + page + ".html");
			
			if(value.hasMember(request.method.toLowerCase())) {
				Value method = value.getMember(request.method.toLowerCase());
			
				if(method.canExecute()) {
					Object[] args = {connection, request, document};
					Value returned = method.execute(args);
					return buildHttpResponse(returned.asString(), document);
				}
			}else {
				return buildMethodNotAllowedResponse(request);
			}
		}
		
		return buildDefaultHttpResponse(request);
	}

	protected HttpResponse buildHttpResponse(Value returned) {
		HttpResponse response = new HttpResponse();		
		response.status = returned.hasMember("status") ? returned.getMember("status").asString() : "200 OK";
		response.body = returned.hasMember("body") ? returned.getMember("body").asString().getBytes() : "".getBytes();
		response.header.put("content-length", ""+response.body.length);		
		return response;
	}
	
	protected HttpResponse buildHttpResponse(String status, Document document) {
		HttpResponse response = new HttpResponse();		
		response.status = status;
		response.body = document.toString().getBytes();
		response.header.put("content-length", ""+response.body.length);		
		return response;
	}
	
	protected HttpResponse buildMethodNotAllowedResponse(HttpRequest request) {
		HttpResponse response = new HttpResponse();
		response.status = "405 METHOD NOT ALLOWED";
		response.body = "405 Method Not Allowed!".getBytes();
		response.header.put("content-length", ""+response.body.length);
		return response;
	}
	
	protected HttpResponse buildDefaultHttpResponse(HttpRequest request) {
		HttpResponse response = new HttpResponse();
		if(request.action.equals("/")) {
			request.action = "/index.html";
		}
		
		byte[] data = FileManager.getFileContent(new File("http/public/" + request.action));
		if(data != null) {
			response.status = "200 OK";
			response.body = data;
		}else {
			response.status = "404 NOT FOUND";
			response.body = "404 NOT FOUND!".getBytes();
		}
		
		response.header.put("content-length", ""+response.body.length);
		return response;
	}
	
	protected String preProcessSource(Value page, String source) {
		StringBuilder newSource = new StringBuilder();
				
		String[] functionNames = source.split("\\}\\}");
		for(int i = 0; i < functionNames.length-1; i++) {
			functionNames[i] = functionNames[i].split("\\{\\{")[1].replace(" ", "");
		}
		
		int funcCount = 0;
		String[] parts = source.split("\\{\\{");
		for(int i = 0; i < parts.length; i++) {
			if(parts[i].contains("}}")) {
				parts[i] = parts[i].split("\\}\\}")[1];
				if(i != 0) {
					if(page.hasMember(functionNames[funcCount])) {
						Value method = page.getMember(functionNames[funcCount]);
						if(method.canExecute()) {
							Value returned = method.execute(args);
							newSource.append(returned);
						}
						System.out.println("Running: " + functionNames[funcCount]);
						funcCount++;
					}else {
						System.err.println("No member: " + functionNames[funcCount]);
					}
				}
			}
			newSource.append(parts[i]);
		}
		
		
		return newSource.toString();
	}
	
	public Document getDocument(Value page, String path) {
		byte[] data = FileManager.getFileContent(new File(path));
		if(data == null) {
			return null;
		}
		
		String source = new String(data);
		if(source.contains("{{") && source.contains("}}")) {
			source = preProcessSource(page, source);
		}
		
		return Jsoup.parse(source);
	}
	
}
