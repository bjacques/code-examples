package sparky.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper
{
	public static Path getClassPathResource(String resourceName)
	{
		URI uri = null;
		try {
			URL file = ClassLoader.getSystemResource(resourceName);
			if(file == null) { 
				throw new RuntimeException("Resource not on the classpath: " + resourceName);
			}
			uri = file.toURI();
		}
		catch(URISyntaxException e) {
			e.printStackTrace();
		}
		return Paths.get(uri);
	}
	
	
	public static String getDirectoryPathFor(String resourceName)
	{
		return getClassPathResource(resourceName).getParent().toString();
	}
	
	
	public static String getFilePathFor(String resourceName)
	{
		return getClassPathResource(resourceName).toString();
	}
}
