package BinhAT.Lesson4_VerifyResponse;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoVerifyUseJsonPath {
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
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code chưa đúng.");
        Assert.assertEquals(response.getContentType(), "application/json", "Content Type chưa đúng.");

        //Muốn lấy giá trị từng key trong JSON body để compare chính xác thì dùng JsonPath
        JsonPath jsonPath = response.jsonPath(); //Lưu hết body vào đối tượng jsonPath

        //Truy xuất giá trị theo key hoặc đường dẫn xpath theo cấp bậc
        String name = jsonPath.get("response.name");
        System.out.println("Name: " + name);
        //Dùng Assert của TestNG để verify
//        Assert.assertEquals(name.contains("Phương Nam"), true, "Name không tồn tại.");
        Assert.assertEquals(name,"Đất Rừng Phương Nam", "Name không tồn tại.");

        //Lấy đường dẫn path thứ 2 trong mảng của object "image"
        //Index bắt đầu tính từ 0
        String imagePath2 = jsonPath.get("response.image[1].path");
        System.out.println(imagePath2);
        Assert.assertEquals(imagePath2.toString(),"public/images/TwSX1W1RgW26ppX3fhDtxVcLV7tsJAooDtxJWBP7.png","Không đúng hình ảnh thứ 2.");
        Assert.assertTrue(jsonPath.get("response.image[1].path").toString().contains("public/images/TwSX1W1"), "Không đúng hình ảnh thứ 2.");
    }

    @Test
    public void TC_verifyGetImageId() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json");
        int id = 1;     // Image id
        Response response = request.when().get("/image/" + id);
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200,"Status code is not match");
        Assert.assertEquals(response.getContentType(),"application/json","Content Type is not match");

        JsonPath jsonPath = response.jsonPath(); //Lưu hết body vào đối tượng jsonPath
        Assert.assertEquals(jsonPath.get("message"),"Success", "TC iss FAIL");

        int imageID = jsonPath.get("response.id");
        System.out.println("Image ID: " + imageID);
        Assert.assertEquals(imageID, 1,"Image ID is not match");
        String imagePath = jsonPath.get("response.path");
        System.out.println("Image path: " + imagePath);
        Assert.assertEquals(imagePath, "public/images/1avh0VncWchVJd1SrYpcgLTU4NccDVW6Ck8ixmW3.png","Image path is not match");


    }
}
