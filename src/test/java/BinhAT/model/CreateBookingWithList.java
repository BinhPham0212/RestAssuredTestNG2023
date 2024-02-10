package BinhAT.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CreateBookingWithList {
    @Data
    @AllArgsConstructor
    @Builder
    public static class BookingBody {
        private String firstname;
        private String lastname;
        private int totalprice;
        private boolean depositpaid;
        private List<BookingDates> bookingDates;
        private String additionalneeds;
    }

    @Data
    @AllArgsConstructor
    public static class BookingDates {
        private String checkin;
        private String checkout;
    }

}
