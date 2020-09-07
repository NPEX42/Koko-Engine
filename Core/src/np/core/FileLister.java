package np.core;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileLister {
	private static List<File> files;
	public static List<File> ListFiles(File parentDir, IFileConsumer consumer) {
		files = new ArrayList<File>();
		consumer.Consume(parentDir);
		return files;
	}
	
	public static void ConsumeFile(File file) {
		if(file.isDirectory()) {
			for(File f : file.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					return (pathname.getName().endsWith(".java"));
				}
			}))
			ListFiles(file, FileLister::ConsumeFile);
		} else {
			files.add(file);
		}
	}
}
