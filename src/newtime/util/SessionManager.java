package newtime.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import newtime.util.format.Hex;

public class SessionManager {

	private static HashMap<String, Session> sessions = new HashMap<String, Session>();
	
	public static String createSession(String uid) {
		String token = null;
		do {
			token = generateToken(uid);
			if(token == null) {return null;}
		}while(sessions.get(token) != null);
		Session session = new Session();
		sessions.put(token, session);
		return token;
	}
	
	private static String generateToken(String uid) {
		byte[] hash = null;
		try {
			String raw  = uid + ":" + System.currentTimeMillis();
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(raw.getBytes());
			hash = digest.digest();
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return Hex.fromByteArray(hash);
	}
	
	public static HashMap<String, Object> getSessionVariables(String token) {
		return sessions.get(token).variables;
	}
	
}

class Session {
		
	public HashMap<String, Object> variables = new HashMap<String, Object>();
		
}
