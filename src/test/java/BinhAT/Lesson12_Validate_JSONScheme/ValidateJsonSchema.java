package BinhAT.Lesson12_Validate_JSONScheme;

import BinhAT.common.BaseTest;
import BinhAT.globals.ConfigsGlobal;
import BinhAT.globals.TokenGlobal;
import BinhAT.helpers.SystemHelper;
import BinhAT.model.RegisterUserPOJO_Lombok;
import BinhAT.model.data_builder.UserPOJO_Lombok_Builder;
import com.fasterxml.jackson.databind.JsonNode;

import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.Option;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.saasquatch.jsonschemainferrer.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static io.restassured.RestAssured.given;

public class ValidateJsonSchema {
    @Test
    public void validateJsonSchema_GetBookById() {
        InputStream GetBookIdSchema = getClass().getClassLoader()
                .getResourceAsStream("GetBookAllSchema.json");

        // Perform the API request and get the response
        Response response = given()
                .baseUri(ConfigsGlobal.BASE_URI)
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
                .baseUri(ConfigsGlobal.BASE_URI)
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
                .baseUri(ConfigsGlobal.BASE_URI)
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

    private static final JsonSchemaInferrer inferrer = JsonSchemaInferrer.newBuilder()
            .setSpecVersion(SpecVersion.DRAFT_07)
            // Requires commons-validator
            .addFormatInferrers(FormatInferrers.email(), FormatInferrers.ip())
            .setAdditionalPropertiesPolicy(AdditionalPropertiesPolicies.notAllowed())
            .setRequiredPolicy(RequiredPolicies.nonNullCommonFields())
            .addEnumExtractors(EnumExtractors.validEnum(java.time.Month.class),
                    EnumExtractors.validEnum(java.time.DayOfWeek.class))
            .build();

    public static void generateSchema(String inputJsonFile, String outputSchemaFile) {
        try {
            // Đọc nội dung JSON từ file
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonData = objectMapper.readTree(new File(inputJsonFile));

            // Suy luận JSON Schema từ dữ liệu JSON
            JsonNode schemaNode = inferrer.inferForSample(jsonData);

            // Ghi schema ra file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputSchemaFile), schemaNode);

            System.out.println("✅ Schema đã được tạo tại: " + outputSchemaFile);
            System.out.println("Output tạo ra: " + schemaNode);
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi xử lý file: " + e.getMessage());
        }

    }

    @Test
    public void GenerateJsonSchema() {
        String inputJsonFile = "src/test/resources/testdata/TestJsonFile01.json"; // File JSON đầu vào
        String outputJsonSchema = "src/test/resources/testdata/TestJsonFile10.json"; // File JSON đầu ra
//        String outputSchemaFile = "schema.json"; // File chứa JSON Schema

        generateSchema(inputJsonFile,outputJsonSchema);
    }


    //https://github.com/saasquatch/json-schema-inferrer?tab=readme-ov-file





//    import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.victools.jsonschema.generator.*;
//import com.github.victools.jsonschema.inferrer.*;
//import java.io.File;
//import java.io.IOException;
//
//    public class JsonSchemaGeneratorUtil {
//        private static final ObjectMapper objectMapper = new ObjectMapper();
//
//        private static final JsonSchemaInferrer inferrer = JsonSchemaInferrer.newBuilder()
//                .setSpecVersion(SpecVersion.DRAFT_07)
//                .addFormatInferrers(FormatInferrers.email(), FormatInferrers.ip())
//                .setAdditionalPropertiesPolicy(AdditionalPropertiesPolicies.notAllowed())
//                .setRequiredPolicy(RequiredPolicies.nonNullCommonFields())
//                .addEnumExtractors(EnumExtractors.validEnum(java.time.Month.class),
//                        EnumExtractors.validEnum(java.time.DayOfWeek.class))
//                .build();
//
//        /**
//         * Hàm generate JSON Schema từ file JSON hoặc class Java.
//         * @param inputSource File JSON hoặc Class Java
//         * @param outputSchemaFile Đường dẫn file schema đầu ra
//         */
//        public static void generateSchema(Object inputSource, String outputSchemaFile) {
//            try {
//                JsonNode schemaNode;
//
//                if (inputSource instanceof String) {
//                    // Xử lý trường hợp input là file JSON
//                    String inputJsonFile = (String) inputSource;
//                    JsonNode jsonData = objectMapper.readTree(new File(inputJsonFile));
//                    schemaNode = inferrer.inferForSample(jsonData);
//                } else if (inputSource instanceof Class) {
//                    // Xử lý trường hợp input là class Java
//                    Class<?> inputClass = (Class<?>) inputSource;
//                    SchemaGeneratorConfig config = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON)
//                            .build();
//                    SchemaGenerator generator = new SchemaGenerator(config);
//                    schemaNode = generator.generateSchema(inputClass);
//                } else {
//                    throw new IllegalArgumentException("❌ Định dạng đầu vào không hợp lệ. Hãy truyền vào đường dẫn file JSON hoặc class Java.");
//                }
//
//                // Ghi schema ra file
//                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputSchemaFile), schemaNode);
//                System.out.println("✅ Schema đã được tạo tại: " + outputSchemaFile);
//                System.out.println("Output tạo ra: " + schemaNode);
//            } catch (IOException e) {
//                System.err.println("❌ Lỗi khi xử lý file: " + e.getMessage());
//            }
//        }
//
//        public static void main(String[] args) {
//            // Ví dụ: Tạo schema từ file JSON
//            generateSchema("src/test/resources/testdata/TestJsonFile.json", "output-schema.json");
//
//            // Ví dụ: Tạo schema từ class Java
//            generateSchema(SampleClass.class, "output-schema-class.json");
//        }
//    }

}



