package newtime.net.gps;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import newtime.net.http.HttpServer;
import newtime.net.tcp.TcpServer;
import newtime.util.FileDictionary;

public class GeneralPurposeServer {
	
	public static void main(String[] args) {
		FileDictionary.load("cfg/FileDictionary.cfg");
		try {
			new GeneralPurposeServer(80);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private HttpServer server;
	
	private Context mainContext;
	
	public GeneralPurposeServer(int port) throws IOException {
		File file = new File("autoexec.js");
		byte[] buffer = new byte[(int)file.length()];
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		in.read(buffer);
				
		mainContext = Context.newBuilder("js").option("js.strict", "true").allowAllAccess(true).build();
		
		ServerBindings serverBindings = new ServerBindings(this.mainContext);
		
		Value bindings = mainContext.getBindings("js");
		bindings.putMember("server", serverBindings);
		
		mainContext.eval("js", new String(buffer));
	}
	
	public Context getMainContext() {
		return this.mainContext;
	}
	
	
}