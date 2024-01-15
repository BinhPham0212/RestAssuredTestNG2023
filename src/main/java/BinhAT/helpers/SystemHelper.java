package BinhAT.helpers;

import java.io.File;

public class SystemHelper {
    public static String getCurrentDir() {
        return System.getProperty("user.dir") + File.separator;
    }

    public static String getCurrentDirDataJson() {
        return System.getProperty("user.dir") + File.separator + "src/test/resources/testdata/";
    }
}
