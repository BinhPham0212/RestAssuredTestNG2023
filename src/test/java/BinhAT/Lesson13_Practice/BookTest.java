package BinhAT.Lesson13_Practice;

import BinhAT.common.BaseTest;
import BinhAT.globals.ConfigsGlobal;
import BinhAT.globals.TokenGlobal;
import BinhAT.helpers.JsonHelper;
import BinhAT.model.BookingPOJO;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class BookTest extends BaseTest {
    int CATEGORY_ID;
    String dataFile = "src/test/resources/testdata/CreateCategory.json";

    @Test(priority = 1)
    public void testAddNewCategory() {
        System.out.println("Create Category");
//        File fileData = new File("src/test/resources/testdata/CreateCategory.json");
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
    public void testAddNewBook() {

        Faker faker = new Faker(new Locale("vn"));

        BookingPOJO bookPOJO = new BookingPOJO();
        bookPOJO.setName(faker.book().title());
        bookPOJO.setCategory_id(CATEGORY_ID);
        bookPOJO.setPrice(120000);
        bookPOJO.setRelease_date("2024-01-28");
        bookPOJO.setStatus(true);

        bookPOJO.setImage_ids(new ArrayList<>(Arrays.asList(2, 18, 19)));

        Gson gson = new Gson();
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ConfigsGlobal.HEADERACCEPT)
                .contentType(ConfigsGlobal.CONTENTTYPE)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(bookPOJO));
        Response response = request.post("/book");
        response.prettyPrint();
        response.then().statusCode(200);
    }
}
