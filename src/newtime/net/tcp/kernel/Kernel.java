package newtime.net.tcp.kernel;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import newtime.net.tcp.TcpServer;

public class Kernel implements Runnable {

	private BufferedReader in;
	
	private TcpServer server;
	
	private Thread thread;
	
	private boolean running = true;
	
	protected ArrayList<Command> commands = new ArrayList<Command>();
	
	public Kernel(TcpServer server) {
		addCommands();
		
		this.in = new BufferedReader(new InputStreamReader(System.in));
		
		this.server = server;
		
		this.thread = new Thread(this);
		this.thread.start();		
	}
	
	public void run() {
		showWatermark();
		while(running) {
			try {
				tick();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		server.close();		
	}
	
	protected void addCommands() {
		commands.add(new CommandHelp());
	}
	
	private void showWatermark() {
		System.out.println("Java General Purpose Server.");
		System.out.println("Use the \"help\" command to show a list of commands.");
	}
	
	private void tick() throws IOException {
		String line = this.in.readLine();
		String[] args = {line};
		if(line.contains(" ")) {
			args = line.split(" ");
		}
		String output = executeCommand(args);
		if(output == null) {
			System.out.println("Could not find command \"" + args[0] + "\"");
		}else {
			System.out.println(output);
		}
	}
	
	private String executeCommand(String[] args) {
		String output = null;
		for(int i = 0; i < commands.size(); i++) {
			if(commands.get(i).name.equals(args[0])) {
				output = commands.get(i).execute(this, args);
				break;
			}
		}
		return output;
	}
	
}
