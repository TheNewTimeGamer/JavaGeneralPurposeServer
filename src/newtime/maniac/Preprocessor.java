package newtime.maniac;

import newtime.net.auth.Session;

public class Preprocessor {

	public static String process(Session session, String raw) {
		raw = processVariables(session, raw);
		raw = processOperators(session, raw);		
		return raw;
	}
	
	private static String processVariables(Session session, String raw) {
		String[] varNames = session.getVariableNames();
		for(String var : varNames){
			String value = session.variables.get(var).toString();
			raw = raw.replace("$"+var+";", value);
		}
		return raw;
	}
	
	private static String processOperators(Session session, String raw) {
		String[] lines = raw.split("\r\n");
		
		int depth = 0;
		for(int i = 0; i < lines.length; i++) {
			String line = lines[i].replace("\t", "");
			while(line.contains("  ")) {
				line = line.replace("  ", "");
			}
			System.out.println(line);
		}
		
		
		return raw;
	}
	
}
