package BinhAT.Lesson7_Authentication_PUTmethod;


import BinhAT.common.BaseTest;
import BinhAT.model.RegisterUserPOJO_Lombok;
import BinhAT.model.verify_response_data.VerifyDataUserBody;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import BinhAT.model.LoginPOJO;
import BinhAT.model.RegisterUser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutMethod_Token {

    //Khai báo biến toàn tục TOKEN để lưu trữ từ hàm Login
    String TOKEN = "";

    @BeforeMethod
    public void loginUser() {

        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
        LoginPOJO loginPOJO = new LoginPOJO("UserName", "Demo@123");

        //Dùng thư viện Gson để chuyển class POJO về dạng JSON
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(loginPOJO));

        Response response = request.when().post("/login");
        response.prettyPrint();

        response.then().statusCode(200);

        //Lưu giá trị token vào biến TOKEN nhé
        TOKEN = response.getBody().path("token");
        System.out.println(TOKEN);
    }

    @Test
    public void testEditUser_BearToken() {

        //Prepare data
        RegisterUserPOJO_Lombok registerUserPOJO = new RegisterUserPOJO_Lombok();
        registerUserPOJO.setUsername("anhtester12");
        registerUserPOJO.setPassword("Demo@12334");
        registerUserPOJO.setFirstName("Lê Thị Thu");
        registerUserPOJO.setLastName("Mỹ Duyên Nồng");
        registerUserPOJO.setEmail("myduyen33@email.com");
        registerUserPOJO.setPhone("012345678999");
        registerUserPOJO.setUserStatus(0);

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer" + TOKEN)
                .body(gson.toJson(registerUserPOJO));

        Response response = request.when().put("/user/6");
        response.prettyPrint();

        System.out.println(response.getStatusCode());
        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");

        VerifyDataUserBody.verifyDataBodyUser(response,registerUserPOJO);
    }
}
