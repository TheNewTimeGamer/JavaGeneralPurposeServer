package newtime.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class FileDictionary {

	private static HashMap<String, String> dictionary = new HashMap<String, String>();
	
	public static String getMeta(String protocol, String extension) {
		return dictionary.get(protocol+":"+extension);
	}
	
	public static String getFileExtensionFromPath(String path) {
		String[] extensions = path.split("\\.");
		return extensions[extensions.length-1];
	}
	
	public static int load(String path) {
		return load(new File(path));
	}
	
	public static int load(File file) {
		System.out.print("Loading file dictionary..");
		if(!file.exists() || !file.isFile()) {
			System.err.println("\r\nFailed to load file dictionary: " + file.getAbsolutePath());
			return -1;
		}
		int count = 0;
		
		byte[] data = ResourceManager.getFileContent(file);
		if(data == null) {
			System.err.println("\r\nFailed to read file dictionary: " + file.getAbsolutePath());
			return -2;
		}
		
		String raw = new String(data);
		while(raw.contains("  ")) {
			raw = raw.replace("  ", " ");
		}
		raw = raw.replace("\t", "");
		raw = raw.replace("\r\n", "");
		
		String[] lines = raw.split(";");
		
		String current = null;
		int mode = 0;
		for(int i = 0; i < lines.length; i++) {
			lines[i] = lines[i].trim();
			if(mode == 0) {
				if(!lines[i].contains(" ")) {continue;}
				String[] args = lines[i].split(" ");
				if(args[0].equals("begin")) {
					current = args[1];
					mode = 1;
					continue;
				}
			}else if(mode == 1) {
				if(lines[i].equals("end")) {
					mode = 0;
					continue;
				}
				if(!lines[i].contains(":")) {continue;}
				String[] args = lines[i].split(":");
				if(args[1].contains(",")) {
					String[] fileExtension = args[1].split(",");
					for(int c = 0; c < fileExtension.length; c++) {
						dictionary.put(current+":"+fileExtension[c].trim(), args[0]);
						count++;
					}
				}else {
					dictionary.put(current+":"+args[1].trim(), args[0]);
					count++;
				}
			}
		}
		System.out.println(" Done! (" + count + " file extensions)");				
		return count;		
	}
	
	
}
