package newtime.net.tcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class TcpConnection implements Runnable {
	
	public static final int DEFAULT_SOCKET_TIMEOUT = 4850;
	public static final int CHUNK_SIZE = 4096;
	
	protected TcpServer server;
	
	protected Socket socket;
	protected InputStream in;
	protected OutputStream out;
	
	protected Thread thread;
	
	protected boolean listening = true;
	
	protected ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	
	public TcpConnection(TcpServer server, Socket socket) throws IOException {
		this.server = server;
		
		this.socket = socket;
		this.in = socket.getInputStream();
		this.out = socket.getOutputStream();
				
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public TcpConnection(TcpServer server, Socket socket, int timeout) throws IOException {
		this.server = server;
		
		this.socket = socket;
		this.socket.setSoTimeout(timeout);
		
		this.in = socket.getInputStream();
		this.out = socket.getOutputStream();
				
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public TcpConnection(String ip, int port) throws IOException {
		Socket socket = new Socket(ip, port);		
		socket.setSoTimeout(DEFAULT_SOCKET_TIMEOUT);
		
		this.socket = socket;
		this.in = socket.getInputStream();
		this.out = socket.getOutputStream();
		
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public TcpConnection(String ip, int port, int timeout) throws IOException {
		Socket socket = new Socket(ip, port);		
		socket.setSoTimeout(timeout);
		
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
			} catch (Exception e) {
				e.printStackTrace();
				listening = false;
			}
		}
		close();
	}
		
	protected void close() {
		this.closeInputStream();
		this.closeOutputStream();
		this.closeSocket();
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
	
	protected void listen() throws Exception {
		byte[] chunk = new byte[CHUNK_SIZE];
		
		int amount = 0;
		do {
			amount = in.read(chunk);
			this.buffer.write(chunk);
		}while(amount > -1 && !(amount < CHUNK_SIZE));
		
		byte[] data = this.buffer.toByteArray();
		this.buffer.reset();
		
		this.onData(data);
	}
	
	protected void onData(byte[] data) {
		String raw = new String(data);
		System.out.println(raw);
	}
	
	public TcpServer getServerInstance() {
		return this.server;
	}
	
	public String getIpAddress() {
		return socket.getInetAddress().getHostAddress();
	}
	
}
