package BinhAT.Lesson3_SendRequest_GETmethod;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DemoAddParam {
    @Test
    public void testUserByUserName() {
        //Khai báo đối tượng request để thiết lập điều kiện đầu vào
        //Dùng given() chỉ thị sự thiết lập sẵn điều kiện
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .basePath("/user")
                .log().all()
                .accept("application/json");

        //Khai báo tham số đầu vào với hàm queryParam
        request.queryParam("username", "theUser");
//        request.param("username","anhtester2");

        Response response = request.when().get();
        response.prettyPrint();

        response.then().statusCode(200);
        response.then().statusLine("HTTP/1.1 200 OK");
        response.then().contentType(ContentType.JSON);

        //body. theo cấp bậc
        response.then().body("response.username",equalTo("theUser"));
        response.then().body("response.email",containsString("vu"));
    }

    @Test
    public void testUserByUserID() {
        //Khai báo đối tượng request để thiết lập điều kiện đầu vào
        //Dùng given() chỉ thị sự thiết lập sẵn điều kiện
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .basePath("/user")
                .accept("application/json");

        //Khai báo tham số đầu vào với hàm queryParam
//        request.queryParam("id", "5");
        request.param("username","anhtester2");

        Response response = request.when().get();
        response.prettyPrint();

        response.then().statusCode(200);
        response.then().statusLine("HTTP/1.1 200 OK");
        response.then().contentType(ContentType.JSON);

        //body. theo cấp bậc
        response.then().body("response.id",equalTo(5));
        response.then().body("response.email",containsString("an1"));
    }
}
