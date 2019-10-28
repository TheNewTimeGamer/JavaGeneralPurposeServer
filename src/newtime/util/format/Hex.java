package newtime.util.format;

public class Hex {

	// %02X  :
	//			'0' - The result will be zero-padded.
	//			'2' - Width of format.
	//			'X' - The result is formatted as a hexadecimal integer, upper-case.
	public static String fromByteArray(byte[] buffer) {
		StringBuilder builder = new StringBuilder();
		for(byte b : buffer) {
			builder.append(String.format("%02X", b));
		}
		return builder.toString();
	}
	
}
