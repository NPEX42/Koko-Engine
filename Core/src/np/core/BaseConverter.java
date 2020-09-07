package np.core;

public class BaseConverter {
	public static String toHexString(int x, int padding) {
		return String.format("%0"+padding+"x", padding);
	}
}
