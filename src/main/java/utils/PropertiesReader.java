package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    public static String getProperty(
            String fileName, String key) {
        Properties properties = new Properties();
        try (FileInputStream fis =
                     new FileInputStream("src/test/properties" +
                             File.separator + fileName)) {
            properties.load(fis);
            return properties.getProperty(key);
        } catch (IOException e) {
            System.out.println("created an exception");
            e.printStackTrace();
            return null;
        }
    }
}