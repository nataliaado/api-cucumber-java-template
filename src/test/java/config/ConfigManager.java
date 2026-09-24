package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream(
                        "environment.properties")) {

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBaseUrl() {
        String environment = System.getProperty("env", "booker");

        return properties.getProperty(environment);
    }
}
