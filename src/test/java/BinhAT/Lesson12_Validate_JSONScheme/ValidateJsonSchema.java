package BinhAT.Lesson12_Validate_JSONScheme;

import BinhAT.common.BaseTest;
import BinhAT.globals.ConfigsGlobal;
import BinhAT.globals.TokenGlobal;
import BinhAT.helpers.SystemHelper;
import BinhAT.model.RegisterUserPOJO_Lombok;
import BinhAT.model.data_builder.UserPOJO_Lombok_Builder;
import com.google.gson.Gson;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;
import java.io.InputStream;

import static io.restassured.RestAssured.given;

public class ValidateJsonSchema {
    @Test
    public void validateJsonSchema_GetBookById() {
        InputStream GetBookIdSchema = getClass().getClassLoader()
                .getResourceAsStream("GetBookAllSchema.json");

        // Perform the API request and get the response
        Response response = given()
                .baseUri(ConfigsGlobal.URI)
                .when()
                .get("/book/10")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .response();

        response.prettyPrint();

        // Validate the response against the JSON schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(GetBookIdSchema));
    }

    @Test
    public void validateJsonSchema_GetBookAll() {
        InputStream GetBookAllSchema = getClass().getClassLoader()
                .getResourceAsStream("GetBookAllSchema.json");

        // Perform the API request and get the response
        Response response = given()
                .baseUri(ConfigsGlobal.URI)
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .response();

        response.prettyPrint();

        // Validate the response against the JSON schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(GetBookAllSchema));
    }

    @Test
    public void validateJsonSchema_GetBookWithFilePath() {
//        InputStream GetBookAllSchema = getClass().getClassLoader()
//                .getResourceAsStream("testdata/GetBookAllSchema.json");

        String filePath = SystemHelper.getCurrentDirDataJson() + "GetBookAllSchema.json";

        // Perform the API request and get the response
        Response response = given()
                .baseUri(ConfigsGlobal.URI)
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .response();

        response.prettyPrint();

        // Validate the response against the JSON schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(filePath)));
    }
}



