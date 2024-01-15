package BinhAT.Lesson3_SendRequest_GETmethod;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoGivenWhenThen {
    @Test
    public void testGetUser() {
        given().baseUri("https://api.anhtester.com/api")
                .accept("application/json")    //Accept data type
                .basePath("/users")
        .when().get()
        .then().statusCode(200).contentType(ContentType.JSON);


    }

}
