package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException {
        if(req==null) {
            PrintStream logfile = new PrintStream(new FileOutputStream("logs.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalPro("BaseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(logfile))
                    .addFilter(ResponseLoggingFilter.logResponseTo(logfile)).setContentType(ContentType.JSON).build();
            return req;
        }
        return req;

    }
    public static String getGlobalPro(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\User\\APIframework\\src\\test\\java\\resources\\global.properties");
        prop.load(fis);
        return prop.getProperty(key);

    }
    public String getJsonpath(String key, Response resp){
        JsonPath jsonPath = new JsonPath(resp.asString());

        return jsonPath.getJsonObject(key);

    }
    public String getJsonResponseAsString(String key, Response response){
        JsonPath jsonPath = new JsonPath(response.asString());

        return jsonPath.getString(key);

    }
}
