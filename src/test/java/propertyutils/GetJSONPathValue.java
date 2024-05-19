package propertyutils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetJSONPathValue {
    public static String getJSONPathValue(Response response, String key) {
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        return jsonPath.get(key).toString();
    }
}
