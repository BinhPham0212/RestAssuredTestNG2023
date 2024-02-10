package BinhAT.Lesson13_Practice;

import BinhAT.common.BaseTest;
import BinhAT.globals.ConfigsGlobal;
import BinhAT.globals.TokenGlobal;
import BinhAT.helpers.JsonHelper;
import BinhAT.helpers.PropertiesHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class CategoryTest extends BaseTest {
    public static int CATEGORY_ID;
    String dataFile = "src/test/resources/testdata/CreateCategory.json";

    @Test(priority = 1)
    public void testAddNewCategory() {
        System.out.println("Create Category");
        Faker faker = new Faker(new Locale("vn"));
        JsonHelper.updateValueJsonFile(dataFile,"name",faker.job().title());

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
//                .basePath("/category")
                .accept(ConfigsGlobal.HEADERACCEPT)
                .contentType(ConfigsGlobal.CONTENTTYPE)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(new File(dataFile));

        Response response = request.post("/category");

        response.prettyPrint();
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        CATEGORY_ID = Integer.parseInt(jsonPath.get("response.id").toString());
    }

    @Test(priority = 2)
    public void testGetCategoryID() {
        System.out.println("Get Category By ID");
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
//                .basePath("/category")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer" + TokenGlobal.TOKEN);

        System.out.println("CATEGORY_ID " + CATEGORY_ID);
        Response response = request.get("/category/" + CATEGORY_ID);

        response.prettyPrint();
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();


        String dataFile = "src/test/resources/testdata/CreateCategory.json";
        Assert.assertEquals(jsonPath.get("response.name"), JsonHelper.readValueJsonObject("name", dataFile), "The name of category is not match");
    }

    @Test(priority = 3)
    public void testUpdateCategoryI() {
        System.out.println("Update Category By ID");
        Response response = BaseTest.putMethodByID(dataFile, "category", CATEGORY_ID);
        JsonPath jsonPath = response.jsonPath();
//        Assert.assertEquals(jsonPath.get("response.name"), JsonHelper.readValueJsonObject("name",dataFile),"The name of category is not match");
        Assert.assertEquals(jsonPath.get("response.name"), JsonHelper.readValueJsonObject("name",dataFile),"The name of category is not match");

    }
}
