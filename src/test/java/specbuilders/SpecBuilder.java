package specbuilders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import propertyutils.PropertyLoader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SpecBuilder {
    public static RequestSpecBuilder requestSpecBuilder;
    public static RequestSpecification requestSpecification;

    public static RequestSpecification setRequestSpec() throws FileNotFoundException {
        if (requestSpecification == null) {
            PrintStream printStream = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpecBuilder = new RequestSpecBuilder()
                    .setBaseUri(PropertyLoader.getPropertyLoader().getBaseUri())
                    .addHeader("Authorization", "Bearer " + PropertyLoader.getPropertyLoader().getApiToken())
                    .setContentType("application/json;charset=utf-8")
                    .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream));
            requestSpecification = requestSpecBuilder.build();
            return requestSpecification;
        } else
            return requestSpecification;
    }
}
