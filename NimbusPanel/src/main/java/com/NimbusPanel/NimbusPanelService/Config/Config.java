package com.NimbusPanel.NimbusPanelService.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private static final String CONFIG_FILE = "config.properties";
	private static Properties props;
	
	static {
	    props = new Properties();
	    try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
	        props.load(input);
	    } catch (IOException e) {
	        System.out.println("config.properties not found — will use API_KEY environment variable if set.");
	    }
	}
	
	public static String getApiKey() {
	    String envKey = System.getenv("API_KEY");
	    if (envKey != null && !envKey.isEmpty()) {
	        return envKey;
	    }
	    return props.getProperty("API_KEY");
	}
}