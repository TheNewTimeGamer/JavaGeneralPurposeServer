package newtime.net.tcp.kernel;

public abstract class Command {
	
	final String name;
	final String description;
	
	public Command(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public abstract String execute(Kernel kernel, String[] args);
	
}
