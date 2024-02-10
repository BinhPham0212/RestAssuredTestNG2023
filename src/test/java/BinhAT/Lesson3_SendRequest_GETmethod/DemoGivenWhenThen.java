package BinhAT.Lesson3_SendRequest_GETmethod;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoGivenWhenThen {
    @Test
    public void testGetUser() {
        given().log().all().baseUri("https://api.anhtester.com/api")
                .accept("application/json")    //Accept data type
                .basePath("/users")
        .when().get()
        .then().log().body().
                statusCode(200).contentType(ContentType.JSON);

    }

    @Test
    public void testKhaiXuan() {
        RequestSpecification request = given();
        request.log().all().baseUri("https://api.anhtester.com/api/")
                .accept("application/json")
                .basePath("users");

                Response response = request.when().get();
        response.prettyPrint();
    }

}
