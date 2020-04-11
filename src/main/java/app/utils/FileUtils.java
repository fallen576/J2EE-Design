package app.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {

	public static String getFileContents(String filename) {
		ClassLoader classLoader = new FileUtils().getClass().getClassLoader();
		 
        File file = new File(classLoader.getResource(filename).getFile());
        try {
			return new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	private FileUtils() {}
	
}
