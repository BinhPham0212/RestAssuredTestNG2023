package BinhAT.Lesson4_VerifyResponse;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;

public class DemoVerifyUseThenMethod {
    @Test
    public void testVerifyResponseUseThenMethod() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json");

        int id = 1; //ID của book. Gắn vào sau path url luôn

        Response response = request.when().get("/book/" + id);
        response.prettyPrint();

        //Verify kết quả từ response với hàm then()
        response.then().statusCode(200)
                .contentType("application/json");
        //Đối với body thì cần điền cấu trúc theo xpath từ json
        //Hàm equalTo thuộc thư viện org.hamcrest.Matchers
        response.then().body("response.name", containsString("Phương Nam"))
                .body("response.price", equalTo(12000));
        //Dùng vị trí index để lấy thứ tự phần tử trong JSON body. Tính từ 0
        response.then().body("response.image[0].path", containsString("public/images/1avh0VncWc"));
    }
}
