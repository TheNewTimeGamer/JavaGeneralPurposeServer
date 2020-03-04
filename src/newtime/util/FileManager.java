package newtime.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import newtime.net.http.response.HttpResponse;

public class FileManager {
	
	private static HashMap<String, ByteBuffer> resources = new HashMap<String, ByteBuffer>();
	
	public static byte[] getFileContent(String path) {
		return getFileContent(new File(path));
	}
	
	public static byte[] getFileContent(File file) {
		byte[] buffer = null;
		try {
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			buffer = new byte[in.available()];
			in.read(buffer);
			in.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
	
	public static byte[] storeResource(String name, String path) {
		return storeResource(name, new File(path));
	}
	
	public static byte[] storeResource(String name, File file) {
		byte[] buffer = getFileContent(file);
		if(buffer == null) {return null;}
		resources.put(name, ByteBuffer.wrap(buffer));
		return buffer;
	}
	
	public static byte[] storeResource(String name, byte[] buffer) {
		ByteBuffer oldBuffer = resources.put(name, ByteBuffer.wrap(buffer));
		if(oldBuffer == null) {return null;}		
		byte[] oldData = new byte[oldBuffer.limit()];
		oldBuffer.get(oldData);		
		return oldData;
	}
	
	public static byte[] getResource(String name) {
		ByteBuffer buffer = resources.get(name);
		if(buffer == null) {return null;}
		byte[] data = new byte[buffer.limit()];
		buffer.get(data);
		return data;
	}
	
	public static boolean releaseResource(String name) {
		ByteBuffer buffer = resources.remove(name);
		if(buffer == null) {return false;}
		return true;
	}

	
}
