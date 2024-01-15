package BinhAT.Lesson4_VerifyResponse;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.StringUtils.contains;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.testng.Assert.*;


public class DemoVerifyUseAssertTestNG {
    @Test
    public void testVerifyResponseUseAssertTestNG() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json");

        int id = 1; //ID của book. Gắn vào sau path url luôn

        Response response = request.when().get("/book/" + id);
        response.prettyPrint();

        //Verify kết quả từ response với Assert trong TestNG
        //Dùng class Assert chấm gọi 2 hàm chính là assertEquals và assertTrue
        assertEquals(response.statusCode(), 200, "Status Code chưa đúng.");
        assertEquals(response.getContentType(), "application/json", "Content Type chưa đúng.");


        //Khi lấy kết quả trong body thì cần dùng đối tượng class ResponseBody để lấy hết về kiểu String
        //Khi đó chỉ có thể dùng hàm contains để so sánh chứa, vì không lấy được từng giá trị của từng key
        ResponseBody body = response.getBody();
        assertEquals(body.asString().contains("Success"), true, "Message Success không tồn tại.");

        String imagePath = body.path("response.image[0].path");
        assertEquals(imagePath,"public/images/1avh0VncWchVJd1SrYpcgLTU4NccDVW6Ck8ixmW3.png", "Image Path không tồn tại.");
        assertTrue(imagePath.contains("public/images/1avh0"),"Image Path không tồn tại.");

        int imageId1 = body.path("response.image[0].id");
        assertEquals(Integer.parseInt(body.path("response.image[0].id").toString()),1,"Image id không tồn tại.");

        //Muốn lấy giá trị từng key trong JSON body để compare chính xác thì
        //phải dùng hàm then() hoặc thư viện JsonPath
   }
}










//        Assert.assertEquals((String.valueOf(response.then().body("response.image[0].path", containsString("public/images/1avh0VncWc")))), "public/images/1avh0VncWchVJd1SrYpcgLTU4NccDVW6Ck8ixmW3.png","The path is not match");

//        Assert.assertEquals((String.valueOf(response.then().body("response.image[0].path", containsString("public/images/1avh0VncWc")))), "public/images/1avh0VncWchVJd1SrYpcgLTU4NccDVW6Ck8ixmW3.png","The path is not match");
//        String imagePath = response.then().body("response.price", equalTo(12000)).toString();
//        System.out.println("Imgae path is: " +imagePath);
//        Assert.assertEquals(imagePath,12000,"Price is not match");