package newtime.net.http.response;

public class HttpQuickResponse extends HttpResponse {

	public void t() {
		
	}
	
	public byte[] toBytes() {
		String defaultResponse = "HTTP/1.1 200 OK\r\nContent-Length: 2\r\n\r\nlo";
		return defaultResponse.getBytes();
	}
	
}
