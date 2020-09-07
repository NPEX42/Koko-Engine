package np.core;
import java.io.File;
import java.io.IOException;
import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
public class IniReader {
	Ini ini;
	public IniReader(File file) throws InvalidFileFormatException, IOException {
		ini = new Ini(file);
		Config cnf = new Config();
		cnf.setMultiOption(true);
	}
	
	public String Get(String section, String key) {
		return ini.get(section, key);
	}
	
	public String[] GetStrings(String section,String key) {
		return ini.get(section).getAll(key, String[].class);
	}
	
	public int[] GetInts(String section,String key) {
		return ini.get(section).getAll(key, int[].class);
	}
	
}
