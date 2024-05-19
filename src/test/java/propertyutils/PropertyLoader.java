package propertyutils;

import java.util.Properties;

public class PropertyLoader {
    private static PropertyLoader propertyLoader;
    private static Properties properties;

    private PropertyLoader() {
        properties = PropertyInstance.getPropertyInstance();
    }

    public static PropertyLoader getPropertyLoader() {
        if (propertyLoader == null) {
            propertyLoader = new PropertyLoader();
        }
        return propertyLoader;
    }

    public String getBaseUri() {
        if (properties != null) {
            return properties.getProperty("baseuri");
        } else
            throw new RuntimeException("Property can't be retrieved");
    }

    public String getApiToken() {
        if (properties != null) {
            return properties.getProperty("apiToken");
        } else
            throw new RuntimeException("Property can't be retrieved");
    }

    public String getAccountId() {
        if (properties != null) {
            return properties.getProperty("accountId");
        } else
            throw new RuntimeException("Property can't be retrieved");
    }

    public Properties getProperties() {
        return properties;
    }
}
