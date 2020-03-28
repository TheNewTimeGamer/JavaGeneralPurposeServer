package newtime.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpConnection implements Runnable {
	
	protected TcpServer server;
	
	protected Socket socket;
	protected InputStream in;
	protected OutputStream out;
	
	protected Thread thread;
	
	protected boolean listening = true;
	
	public TcpConnection(TcpServer server, Socket socket) throws IOException {
		this.server = server;
		
		this.socket = socket;
		this.in = socket.getInputStream();
		this.out = socket.getOutputStream();
		
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public TcpConnection(String ip, int port) throws IOException {
		Socket socket = new Socket(ip, port);		
		this.socket = socket;
		this.in = socket.getInputStream();
		this.out = socket.getOutputStream();
		
		this.thread = new Thread(this);
		this.thread.start();
	}

	// TODO: Maybe keep connection alive somehow without huge cpu usage?
	public void run() {
		while(listening) {
			try {
				if(!listen()) {
					listening = false;
				}
			} catch (IOException e) {
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
	
	protected boolean listen() throws IOException {
		int available = (int) in.available();
		if(available > 0) {
			byte[] buffer = new byte[available];
			in.read(buffer);
			onData(buffer);
			return true;
		}
		return false;
	}
	
	protected void onData(byte[] data) {
		String raw = new String(data);
		System.out.println(raw);
	}
	
	public TcpServer getServerInstance() {
		return this.server;
	}
	
}
