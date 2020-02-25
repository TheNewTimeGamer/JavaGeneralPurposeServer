package newtime.dom;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Document {

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.moonquest.com");
		InputStream in = url.openConnection().getInputStream();
		
		while(in.available() <= 0) {}
		byte[] buffer = new byte[in.available()];
		in.read(buffer);
		
		String raw = new String(buffer);
		
		Document document = new Document(raw);		
		System.out.println(document.toString());
	}
	
	public Element rootElement = new Element(null, "root");
	
	public Document() {}
	
	public Document(String raw) {
		parseHtml(raw);
	}
	
	public void parseHtml(String raw) {
		Element currentElement = rootElement;
		
		char[] chars = raw.toCharArray();
		
		boolean inString = false;
		
		for(int i = 0; i < chars.length; i++) {
			if(chars[i] == '"') {inString = !inString;}
			if(inString) {continue;}
			
			if(chars[i] == '<') {
				if(chars[i+1] == '/') {
					currentElement = currentElement.parent;
					i++;
				} else {
					Element childElement = buildElementFrom(currentElement, i, chars);
					currentElement.children.add(childElement);
					currentElement = childElement;
				}
			}
			
			if(chars[i] == '/') {
				if(chars[i+1] == '>') {
					currentElement = currentElement.parent;
					i++;
				}
			}
			
		}
			
	}
	
	public Element buildElementFrom(Element parent, int index, char[] chars) {
		String attributeName = "";
		String attributeValue = "";
		
		
		boolean tagMode = true;
		boolean inString = false;
		
		HashMap<String, String> attributes = new HashMap<String, String>();
		
		String tag = "";
		for(int i = index+1; i < chars.length; i++) {
			if(chars[i] == '"') {inString = !inString; continue;}
			if(!inString) {
				if(chars[i] == '>' || (chars[i] == '/' && chars[i] == '>')) {break;}
				if(chars[i] == ' ') {tagMode = false;}
				if(tagMode) {
					tag = tag + chars[i];
				}else {
					if(chars[i] == ' ') {
						if(attributeName != "") {
							attributes.put(attributeName, attributeValue);
						}
						continue;
					}
					if(chars[i] != '=') {
						attributeName = attributeName + chars[i];
					}
				}
			}else {
				attributeValue = attributeValue + chars[i];
			}
		}
		
		Element element = new Element(parent, tag);
		element.attributes = attributes;
		
		return element;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(rootElement.toString());
		
		return builder.toString();
	}
	
}
