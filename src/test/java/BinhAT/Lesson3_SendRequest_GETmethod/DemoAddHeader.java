package BinhAT.Lesson3_SendRequest_GETmethod;

import BinhAT.globals.ConfigsGlobal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoAddHeader {
    @Test
    public void testAddHeader() {
        //Khai báo đối tượng request
        RequestSpecification request = given();
                request.baseUri(ConfigsGlobal.BASE_URI)
                .basePath("/users");
        //Add header theo yêu cầu (syntax: key-value)
        request.header("accept", ConfigsGlobal.HEADERACCEPT);
        //request.accept("application/json");

        Response response = request.when().get();

        //In ra giá trị
        response.prettyPrint();
        response.then().statusCode(200);
    }
}
