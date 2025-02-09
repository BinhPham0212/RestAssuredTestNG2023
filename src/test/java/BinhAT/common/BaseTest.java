package BinhAT.common;

import BinhAT.globals.ConfigsGlobal;
import BinhAT.globals.EndPointGlobal;
import BinhAT.globals.TokenGlobal;
import BinhAT.helpers.JsonHelper;
import BinhAT.helpers.PropertiesHelper;
import BinhAT.keywords.ApiKeyword;
import BinhAT.listeners.TestListener;
import BinhAT.model.data_builder.LoginPOJO_Builder;
import BinhAT.reports.AllureManager;
import BinhAT.utils.LogUtils;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import BinhAT.model.LoginPOJO;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;

import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeTest
    public void loginUser() {
        LogUtils.info("********LOGIN USER********");
//        LoginPOJO loginPOJO = new LoginPOJO(ConfigsGlobal.USERNAME, ConfigsGlobal.PASSWORD);
        LoginPOJO loginPOJO = LoginPOJO_Builder.getDataLogin();
        Gson gson = new Gson();

//        RequestSpecification request = given();
//        request.baseUri(ConfigsGlobal.BASE_URI)
//                .accept(ConfigsGlobal.HEADERACCEPT)
//                .contentType(ConfigsGlobal.CONTENTTYPE)
//                .body(gson.toJson(loginPOJO));
//
//        Response response = request.when().post("/login");
        Response response = ApiKeyword.postNotAuth(EndPointGlobal.EP_LOGIN, gson.toJson(loginPOJO));
        response.then().statusCode(200);
        //Lưu giá trị token vào biến TOKEN nhé
        TokenGlobal.TOKEN = ApiKeyword.getResponseKeyValue(response,"token");
        LogUtils.info("Token Global: " + TokenGlobal.TOKEN);
        AllureManager.saveTextLog("Token Global: " + TokenGlobal.TOKEN);
    }


    public static void patchMethod_UserID(Object objectData, int userId) {
        Gson gson = new Gson();
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.BASE_URI)
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

    public static void putMethodByID(Object objectData, String item, int itemId) {
        Gson gson = new Gson();
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.BASE_URI)
                .accept(ConfigsGlobal.HEADERACCEPT)
                .contentType(ConfigsGlobal.CONTENTTYPE)
                .header("Authorization","Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(objectData));

        Response response = request.when().put("/"+item+"/" +itemId+ "");
        response.prettyPrint();

        //Verify response
        response.then().statusCode(200);
        response.then().contentType(ContentType.JSON);

        String message = response.getBody().path("message");
        Assert.assertEquals(message,"Success","The message not match");

        ResponseBody responseBody = response.getBody();
    }

    public static Response putMethodByID(String filePath, String item, int itemId) {
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.BASE_URI)
                .accept(ConfigsGlobal.HEADERACCEPT)
                .contentType(ConfigsGlobal.CONTENTTYPE)
                .header("Authorization","Bearer " + TokenGlobal.TOKEN)
                .body(new File(filePath));

        Response response = request.when().put("/"+item+"/" +itemId);
        response.prettyPrint();

        //Verify response
        response.then().statusCode(200);
        response.then().contentType(ContentType.JSON);

        JsonPath jsonPath = response.jsonPath();

        String message = response.getBody().path("message");
        Assert.assertEquals(message,"Success","The message not match");
        Assert.assertEquals(jsonPath.get("response.name"), JsonHelper.readValueJsonObject("name","src/test/resources/testdata/CreateCategory.json"),"The name of category is not match");

        return response;

    }
}
