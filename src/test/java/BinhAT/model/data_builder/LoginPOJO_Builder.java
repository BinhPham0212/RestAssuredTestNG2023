package BinhAT.model.data_builder;

import BinhAT.globals.ConfigsGlobal;
import BinhAT.model.LoginPOJO;

public class LoginPOJO_Builder {
    //Class de tạo data cho Login POJO
    public static LoginPOJO getDataLogin() {

        return LoginPOJO.builder()
                .username(ConfigsGlobal.USERNAME)
                .password(ConfigsGlobal.PASSWORD)
                .build();
    }

    public static String josnDataLogin() {
        return
                "{\n" +
                        "   \"username\": \"anhtester\",\n" +
                        "   \"password\": \"Demo@123\"\n" +
                        "}";

    }
}
