package propertyutils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyInstance {
        public static Properties getPropertyInstance() {
            Properties properties = new Properties();
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader("src/test/resources/globalValues.properties"));
                properties.load(bufferedReader);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return properties;
        }
    }


