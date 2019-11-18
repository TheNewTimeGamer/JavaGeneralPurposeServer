package newtime.maniac;

import newtime.net.auth.Session;

public class Preprocessor {

	public static String process(Session session, String raw) {
		String[] varNames = session.getVariableNames();
		for(String var : varNames){
			String value = session.variables.get(var).toString();
			raw = raw.replace("$"+var+";", value);
		}
		return raw;
	}
	
}
