package configuration;
//url-re : jdbc:sqlite:

import java.io.IOException;
import java.util.Properties;

public class ProjectConfig {
    private static Properties properties = new Properties();


    static {
        try {
            properties.load(ProjectConfig.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getValue(String k){
        return properties.getProperty(k);
    }
}
