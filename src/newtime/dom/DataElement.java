package newtime.dom;

public class DataElement extends Element{

	public byte[] content;
	
	public DataElement(Element parent, String tag, byte[] content) {
		super(parent, tag);
		this.content = content;
	}
	
	public String getContent() {
		return new String(this.content);
	}
	
}
