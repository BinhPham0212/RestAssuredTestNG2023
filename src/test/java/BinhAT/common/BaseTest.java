package BinhAT.common;

import BinhAT.globals.ConfigsGlobal;
import BinhAT.globals.TokenGlobal;
import BinhAT.helpers.PropertiesHelper;
import BinhAT.model.data_builder.LoginPOJO_Builder;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import BinhAT.model.LoginPOJO;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseTest {
    @BeforeSuite
    public void setupSuite() {
        PropertiesHelper.loadAllFiles();
    }

    @BeforeTest
    public void loginUser() {
//        LoginPOJO loginPOJO = new LoginPOJO(ConfigsGlobal.USERNAME, ConfigsGlobal.PASSWORD);
        LoginPOJO loginPOJO = LoginPOJO_Builder.getDataLogin();
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ConfigsGlobal.HEADERACCEPT)
                .contentType(ConfigsGlobal.CONTENTTYPE)
                .body(gson.toJson(loginPOJO));

        Response response = request.when().post("/login");
        response.then().statusCode(200);

        //Lưu giá trị token vào biến TOKEN nhé
        TokenGlobal.TOKEN = response.getBody().path("token");
        System.out.println("Token Global: " + TokenGlobal.TOKEN);
    }


    public void patchMethod_UserID(Object objectData, int userId) {
        Gson gson = new Gson();
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ConfigsGlobal.HEADERACCEPT)
                .contentType(ConfigsGlobal.CONTENTTYPE)
                .header("Authorization","Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(objectData));

        Response response = request.when().patch("/user/" +userId+ "");
        response.prettyPrint();

        //Verify response
        response.then().statusCode(200);
        response.then().contentType(ContentType.JSON);

        String message = response.getBody().path("message");
        Assert.assertEquals(message,"Success","The message not match");

        ResponseBody responseBody = response.getBody();


    }
}
