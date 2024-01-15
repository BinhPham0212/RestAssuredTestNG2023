package BinhAT.Lesson8_PATCH_DELETE;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

import BinhAT.common.BaseTest;
import BinhAT.model.PatchUserPOJO;
import org.testng.annotations.Test;

public class Patch_UpdateUser extends BaseTest {

    @Test
    public void test_UpdateUser() {
        PatchUserPOJO patchUserPOJO = new PatchUserPOJO();
        patchUserPOJO.setFirstName("Laverne edited1234");
        patchUserPOJO.setLastName("Fahey edited1234");
        patchUserPOJO.setEmail("Shirley77@example.com.vn");
        patchUserPOJO.setPhone("07553314228");
        patchUserPOJO.setUserStatus(0);

        int userId = 36;
        BaseTest baseTest = new BaseTest();
        baseTest.patchMethod_UserID(patchUserPOJO,userId);

//        ResponseBody responseBody = request().when().patch("/user/" +userId+ "").getBody();
//        System.out.println(responseBody.prettyPrint());
//        Assert.assertEquals(responseBody.path("response.firstName"), new PatchUserPOJO().getFirstName(),"The first name is not match");
//        Assert.assertEquals(responseBody.path("response.lastName"), new PatchUserPOJO().getLastName(),"The last name is not match");
//        Assert.assertEquals(responseBody.path("response.email"), new PatchUserPOJO().getEmail(),"The email is not match");
//        Assert.assertEquals(responseBody.path("response.phone"), new PatchUserPOJO().getPhone(),"The phone is not match");
//        Assert.assertEquals(Integer.parseInt(responseBody.path("response.userStatus")), 66,"The status is not match");


    }
}
