package np.core;

import java.io.*;


public class IO {
	public static String LoadString(InputStream stream) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
			String line;
			StringBuffer buffer = new StringBuffer();
			while((line = reader.readLine()) != null) {
				buffer.append(line + '\n');
			}
			return buffer.toString();
		} catch(Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
	
	public static String LoadString(String path) {
		try {
			return LoadString(new FileInputStream(path));
		} catch(Exception ex) {
			InputStream stream = IO.class.getResourceAsStream(path);
			if(stream == null) {
				stream = IO.class.getClassLoader().getResourceAsStream(path);
			}
			
			if(stream != null) {
				return LoadString(stream);
			} else {
				return null;
			}
		}
	}
	
}
