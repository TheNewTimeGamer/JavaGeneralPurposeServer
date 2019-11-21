package newtime.net.instancing;

import java.util.HashMap;

public class Session {
	
	public String[] getVariableNames() {
		Object[] sets = variables.keySet().toArray();
		String[] names = new String[sets.length];
		for(int i = 0; i < sets.length; i++) {
			names[i] = sets[i].toString();
		}
		return names;
	}
	
	public HashMap<String, Object> variables = new HashMap<String, Object>();
	
}