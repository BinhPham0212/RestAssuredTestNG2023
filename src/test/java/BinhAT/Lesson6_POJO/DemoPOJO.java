package BinhAT.Lesson6_POJO;

import BinhAT.common.BaseTest;
import BinhAT.globals.ConfigsGlobal;
import BinhAT.model.data_builder.LoginPOJO_Builder;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import BinhAT.model.LoginPOJO;
import org.testng.annotations.Test;
import BinhAT.model.LoginPOJO;

import java.io.File;

import static io.restassured.RestAssured.given;

public class DemoPOJO extends BaseTest {
    @Test
    public void testLoginUser() {

//        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
//        LoginPOJO loginPOJO = new LoginPOJO("anhtester4", "Demo@123");
        LoginPOJO loginPOJO = LoginPOJO_Builder.getDataLogin();

        //Dùng thư viện Gson để chuyển class POJO về dạng JSON
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(loginPOJO));     //  Chuyển từ POJO class sang dạn JSON data
//              .body(LoginPOJO_Builder.josnDataLogin());


        Response response = request.when().post("/login");
        response.prettyPrint();

        response.then().statusCode(200);
        response.then().contentType(ConfigsGlobal.CONTENTTYPE);

        String token = response.getBody().path("token");
        System.out.println(token);
    }

    @Test
    public void testLoginUserWithFileJson() {

//        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
        String filePath = "src/test/resources/testdata/Login.json";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(new File(filePath));     //  Chuyển từ POJO class sang dạn JSON data
//              .body(LoginPOJO_Builder.josnDataLogin());


        Response response = request.when().post("/login");
        response.prettyPrint();

        response.then().statusCode(200);
        response.then().contentType(ConfigsGlobal.CONTENTTYPE);

        String token = response.getBody().path("token");
        System.out.println(token);
    }


}
