package BinhAT.Lesson5_PostMethod;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoPostMethod {
    @Test
    public void testLoginUser() {
        RequestSpecification request = given().baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body("{\n" +
                        "  \"username\": \"binhpd5\",\n" +
                        "  \"password\": \"Demo@123\"\n" +
                        "}");

        //Thực hiện phương thức post() để gửi dữ liệu đi
        Response response = request.when().post("/login");
        response.prettyPrint();

        String token = response.getBody().path("token").toString();
        System.out.println(token);

        response.then().statusCode(200);
    }

    @Test
    public void testRegisterUser() {
        String username = "binhpd6";
        String password = "Demo@123";
        String firstName = "John";
        String lastName = "James";
        String email = username + "@email.com";
        String phone = "0368922112";
        int userStatus = 1;

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body("{\n" +
                        "    \"username\": \"" + username + "\",\n" +
                        "    \"firstName\": \"" + password + "\",\n" +
                        "    \"lastName\": \"" + lastName + "\",\n" +
                        "    \"email\": \"" + username + "@email.com\",\n" +
                        "    \"password\": \"" + password + "\",\n" +
                        "    \"phone\": \"" + phone + "\",\n" +
                        "    \"userStatus\": "+userStatus+"\n" +
                        "  }");

        //Thực hiện phương thức post() để gửi dữ liệu đi
        Response response = request.when().post("/register");
        response.prettyPrint();
        response.then().statusCode(200);


        //Verify response
        response.then().contentType(ContentType.JSON);
        ResponseBody responseBody = response.getBody();
        Assert.assertEquals(responseBody.path("response.username"),username,"Username not match");
        Assert.assertEquals(responseBody.path("response.password").toString().length() > 0,password,"The ID is not exiting");

    }
}
