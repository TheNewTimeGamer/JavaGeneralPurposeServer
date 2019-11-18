package newtime.net.http.response;

public class HttpResponseSwitchingProtocols extends HttpResponse {

	public HttpResponseSwitchingProtocols(String wsAcceptKey) {

		this.status = "101 Switching Protocols";
		this.body = "".getBytes();
		this.header.put("Connection", "upgrade");
		this.header.put("Upgrade", "websocket");
		this.header.put("Sec-WebSocket-Accept", wsAcceptKey);

	}
	
}
