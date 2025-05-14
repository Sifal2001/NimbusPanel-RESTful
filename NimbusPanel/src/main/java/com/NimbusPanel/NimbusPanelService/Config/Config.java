package com.NimbusPanel.NimbusPanelService.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private static final String CONFIG_FILE = "config.properties";
	private static Properties props;
	
	static {
		props = new Properties();
		try(FileInputStream input = new FileInputStream(CONFIG_FILE)){
			props.load(input);
		} catch (IOException e) {
			System.err.print("Failed to load config file" + CONFIG_FILE);
			e.printStackTrace();
		}
	}
	
	public static String getApiKey() {
		return props.getProperty("API_KEY");
	}
}