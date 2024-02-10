package BinhAT.model.data_builder;

import BinhAT.model.CreateBookingWithList;

import java.util.List;

public class CreateBookingWithListBuilder {
//    public static CreateBookingWithList.BookingDates getBookingDatesList() {
//        return
//                CreateBookingWithList.BookingDates.builder()
//                        .checkin("2022-01-01")
//                        .checkout("2022-01-10")
//                        .build();
//    }
    public static CreateBookingWithList.BookingBody getDataBooking() {
//        CreateBookingWithList createBookingWithList = new CreateBookingWithList();
        CreateBookingWithList.BookingDates bookingDate1 = new CreateBookingWithList.BookingDates("2022-01-01", "2022-01-10");

        // Tạo danh sách BookingDates
        List<CreateBookingWithList.BookingDates> bookingDatesList = List.of(bookingDate1);
        return
                CreateBookingWithList.BookingBody.builder()
                        .firstname("John")
                        .lastname("Doe")
                        .totalprice(100)
                        .depositpaid(true)
                        .bookingDates(bookingDatesList)
                        .additionalneeds("Extra bed")
                        .build();




    }
}
