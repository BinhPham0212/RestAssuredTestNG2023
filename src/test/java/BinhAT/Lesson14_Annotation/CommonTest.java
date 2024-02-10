package BinhAT.Lesson14_Annotation;

import org.testng.annotations.*;

public class CommonTest {

    @BeforeClass
    public void beforeBaseClass() {
        System.out.println("Parent Before Class method");
    }

    @AfterClass
    public void afterBaseClass() {
        System.out.println("Parent After Class method");
    }

    @BeforeMethod
    public void beforeBaseMethod() {
        System.out.println("Parent Before method");
    }

    @AfterMethod
    public void afterBaseMethod() {
        System.out.println("Parent After method");
    }

}
