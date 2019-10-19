package newtime.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TcpServer implements Runnable {

	public static TcpServer host(int port) {
		TcpServer server = null;
		try {
			server = new TcpServer(port);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return server;
	}
	
	protected ArrayList<TcpConnection> connections = new ArrayList<TcpConnection>();
	
	protected ServerSocket server;
	protected InputStream in;
	protected OutputStream out;
	
	protected Thread thread;
	
	protected boolean listening = true;
	
	protected TcpServer(int port) throws IOException {
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
		this.closeInputStream();
		this.closeOutputStream();
		this.closeServer();
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
		TcpConnection connection = TcpConnection.create(this, socket);
		return connection;
	}
	
}
