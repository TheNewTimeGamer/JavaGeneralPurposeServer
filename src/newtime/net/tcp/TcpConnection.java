package newtime.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpConnection implements Runnable {

	public static TcpConnection create(TcpServer server, String ip, int port) {
		TcpConnection connection = null;
		try {
			connection = new TcpConnection(server, ip, port);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static TcpConnection create(TcpServer server, Socket socket) {
		TcpConnection connection = null;
		try {
			connection = new TcpConnection(server, socket);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	protected TcpServer server;
	
	protected Socket socket;
	protected InputStream in;
	protected OutputStream out;
	
	protected Thread thread;
	
	protected boolean listening = true;
	
	protected TcpConnection(TcpServer server, Socket socket) throws IOException {
		this.server = server;
		
		this.socket = socket;
		this.in = socket.getInputStream();
		this.out = socket.getOutputStream();
		
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	protected TcpConnection(TcpServer server, String ip, int port) throws IOException {
		this.server = server;
		
		Socket socket = new Socket(ip, port);		
		this.socket = socket;
		this.in = socket.getInputStream();
		this.out = socket.getOutputStream();
		
		this.thread = new Thread(this);
		this.thread.start();
	}

	public void run() {
		while(listening) {
			try {
				listen();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		close();
	}
		
	protected void close() {
		this.closeInputStream();
		this.closeOutputStream();
		this.closeSocket();
		this.joinThread();
		this.thread = null;
	}
			
	private boolean closeInputStream() {
		try {
			this.in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean closeOutputStream() {
		try {
			this.out.flush();
			this.out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean closeSocket() {
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean joinThread() {
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	protected void listen() throws IOException {
		int available = (int) in.available();
		if(available > 0) {
			byte[] buffer = new byte[available];
			in.read(buffer);
			onData(buffer);
		}
	}
	
	protected void onData(byte[] data) {
		String raw = new String(data);
		System.out.println(raw);
	}
	
}
