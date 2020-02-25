package newtime.dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Praise jesus, fire

public class Element {
	
	public HashMap<String, String> attributes = new HashMap<String, String>();	
	public ArrayList<Element> children = new ArrayList<Element>();
	
	public Element parent;
	public String tag;
	
	public Element(Element parent, String tag) {
		this.parent = parent;
		this.tag = tag;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<" + tag + getAttributes() + ">");			
		builder.append(this.getContent());		
		builder.append("</" + tag + ">");		
		return builder.toString();
	}
	
	protected String getContent() {
		StringBuilder builder = new StringBuilder();
				
		for(int i = 0; i < children.size(); i++) {
			builder.append(children.get(i).toString());
		}
				
		return builder.toString();
	}
	
	protected String getAttributes() {
		StringBuilder builder = new StringBuilder();
		
		if(attributes.size() > 0) {
			builder.append(" ");
		}
		
		int count = 0;
		for(Map.Entry<String, String> entry : attributes.entrySet()) {
			count++;
			builder.append(entry.getKey() + "=\"" + entry.getValue() + "\"");
			if(count < attributes.size()) {
				builder.append(" ");
			}
		}
				
		return builder.toString();
	}
	
}
