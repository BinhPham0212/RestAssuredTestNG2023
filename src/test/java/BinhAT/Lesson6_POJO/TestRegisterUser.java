package BinhAT.Lesson6_POJO;

import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import BinhAT.model.RegisterUser;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestRegisterUser {
    @Test
    public void testRegisterUser() {

        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
        RegisterUser registerUser = new RegisterUser();
        registerUser.setUsername("myduyen123");
        registerUser.setPassword("Demo@12345");
        registerUser.setFirstName("Lê Thị");
        registerUser.setLastName("Mỹ Duyên");
        registerUser.setEmail("myduyen12345@email.com");
        registerUser.setPhone("0123456789");
        registerUser.setUserStatus(1);

        //Dùng thư viện Gson để chuyển class POJO về dạng JSON
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(registerUser));

        Response response = request.when().post("/register");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }
    
}
