package com.lms.utilities;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class ConfigReader {
	
	
	private static Properties prop;	
	
	public static void load_prop() {
		
		prop = new Properties();
		
		try {
			InputStream is = ConfigReader.class.getResourceAsStream("/config/config.properties");
			prop.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getProp(String propName) {
		
		return prop.getProperty(propName);
	}
			
		
}
