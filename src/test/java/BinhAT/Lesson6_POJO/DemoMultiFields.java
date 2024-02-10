package BinhAT.Lesson6_POJO;
import BinhAT.model.CreateBookingWithList;
import BinhAT.model.data_builder.CreateBookingWithListBuilder;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import BinhAT.model.BookingBody;
import BinhAT.model.BookingDates;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class DemoMultiFields {
    @Test
    public void testCreateBooking() {

        String baseUri = "https://restful-booker.herokuapp.com";

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header("Accept", "application/json")
                .header("Content-Type", "application/json");

        //Khởi tạo 2 class POJO
        BookingBody bookingBody = new BookingBody();
        BookingDates bookingDates = new BookingDates();

        //Set giá trị cho các fields không chứa cấp bậc
        bookingBody.setFirstname("Anh");
        bookingBody.setLastname("Tester");
        bookingBody.setTotalprice(100);
        bookingBody.setDepositpaid(true);
        bookingBody.setAdditionalneeds("Technology");

        //Set giá trị cho 2 fields con từ class POJO phụ
        bookingDates.setCheckin("2023-12-15");
        bookingDates.setCheckout("2023-12-30");

        //Set giá trị cho field Cha với 2 thông số từ fields Con
        bookingBody.setBookingdates(bookingDates);

        Gson gson = new Gson();

        //Convert POJO to JSON
        request.body(gson.toJson(bookingBody));

        Response response = request.post("/booking");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void testCreateBookingWithBuilder() {

        String baseUri = "https://restful-booker.herokuapp.com";

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header("Accept", "application/json")
                .header("Content-Type", "application/json");

        CreateBookingWithList.BookingBody bookingBodyBuilder = CreateBookingWithListBuilder.getDataBooking();
        Gson gson = new Gson();

        //Convert POJO to JSON
        request.body(gson.toJson(bookingBodyBuilder));

        Response response = request.post("/booking");
        response.prettyPrint();
        response.then().statusCode(200);
    }
}
