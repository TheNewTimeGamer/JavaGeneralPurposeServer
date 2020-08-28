package newtime.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.graalvm.polyglot.Context;

public class TcpServer implements Runnable {

	protected ServerSocket server;
	
	protected Thread thread;
	
	protected boolean listening = true;
	
	protected Context mainContext;
	
	public TcpServer(Context mainContext, int port) throws IOException {
		this.server = new ServerSocket(port);
		this.mainContext = mainContext;
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
	
	public void open() {
		this.thread = new Thread(this);
		this.thread.start();
	}	
	
	public void close() {
		this.listening = false;
		this.closeServer();
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
