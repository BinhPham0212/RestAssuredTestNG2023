package BinhAT.model.verify_response_data;

import BinhAT.model.RegisterUserPOJO_Lombok;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class VerifyDataUserBody {
    public static void verifyDataBodyUser(Response response, RegisterUserPOJO_Lombok registerUserPOJOLombok) {
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("response.username"), registerUserPOJOLombok.getUsername(), "The username not match.");
        Assert.assertEquals(jsonPath.get("response.firstName"), registerUserPOJOLombok.getFirstName(), "The firstName not match.");
        Assert.assertEquals(jsonPath.get("response.lastName"), registerUserPOJOLombok.getLastName(), "The lastName not match.");
        Assert.assertEquals(jsonPath.get("response.email"), registerUserPOJOLombok.getEmail(), "The email not match.");
        Assert.assertEquals(jsonPath.get("response.phone"), registerUserPOJOLombok.getPhone(), "The phone not match.");
        Assert.assertEquals(Integer.parseInt(jsonPath.get("response.userStatus").toString()), registerUserPOJOLombok.getUserStatus(), "The userStatus not match.");
        Assert.assertTrue(jsonPath.get("response.id").toString().length() > 0, "The ID not exsiting.");
    }
}
