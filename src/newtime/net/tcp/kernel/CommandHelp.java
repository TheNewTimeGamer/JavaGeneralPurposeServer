package newtime.net.tcp.kernel;

public class CommandHelp extends Command {

	public CommandHelp() {
		super("help","show a list of all available commands.");
	}
	
	public String execute(Kernel kernel, String[] args) {
		String full = "Commands:\r\n";
		for(int i = 0; i < kernel.commands.size(); i++) {
			full = full + " - " + kernel.commands.get(i).name + "\t\t" + kernel.commands.get(i).description;
		}		
		return full;
	}

	
	
}
