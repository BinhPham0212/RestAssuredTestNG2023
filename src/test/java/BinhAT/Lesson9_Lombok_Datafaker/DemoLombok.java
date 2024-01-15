package BinhAT.Lesson9_Lombok_Datafaker;

import BinhAT.model.RegisterUserPOJO_Lombok;
import org.testng.annotations.Test;

public class DemoLombok {
    @Test
    public void testLombok() {
        RegisterUserPOJO_Lombok registerUserPOJO_lombok = new RegisterUserPOJO_Lombok();


        registerUserPOJO_lombok.getUserStatus();
        RegisterUserPOJO_Lombok registerUserPOJO_lombok12 = new RegisterUserPOJO_Lombok(
                "dfhgs",
                "sdfgsdfg",
                "Phạm"


        );

//        RegisterUserPOJO_Lombok registerUserPOJO_lombok1 = new RegisterUserPOJO_Lombok(
//                "",
//                "",
//                "",
//                "",
//                "",
//                "",
//                "";
//        );

    }
}
