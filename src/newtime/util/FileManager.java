package newtime.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import newtime.net.http.resource.BinaryResource;
import newtime.net.http.resource.HtmlResource;
import newtime.net.http.resource.Resource;
import newtime.net.http.response.HttpResponse;

public class FileManager {
	
	// TODO: Look at return for storeHtmlResource
		
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
			System.err.println("Can't open file: \"" + file.getAbsolutePath() + "\"");
		}
		return buffer;
	}
		
}
