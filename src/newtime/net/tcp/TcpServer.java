package newtime.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TcpServer implements Runnable {

	protected ServerSocket server;
	
	protected Thread thread;
	
	protected boolean listening = true;
	
	public TcpServer(int port) throws IOException {
		this.server = new ServerSocket(port);
				
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
	
	protected void listen() throws IOException {
		Socket socket = server.accept();
		if(socket != null) {
			onConnection(socket);
		}
	}
	
	public void close() {
		this.listening = false;
		this.closeServer();
		this.thread = null;
	}
	
	private boolean closeServer() {
		try {
			this.server.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
		
	public TcpConnection onConnection(Socket socket) {
		TcpConnection connection = null;
		try {
			connection = new TcpConnection(this, socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
}
